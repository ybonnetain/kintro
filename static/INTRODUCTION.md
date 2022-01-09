# Kintro

Kintro is intended at giving a first introduction to kotlin multiplatform to a development / product team. \
It is a minimal project visiting the counter and the todo list, the most common tutorials of front-end development.

## Multiplatform programming module

After analyzing a requirement you must determine what part of a feature you can share in term of code and what you cannot. \
This is a must do with Kotlin multiplatform programming (MPP) and I like it. \
You can only benefit from a strong conception phase in a project.

Let's look at the *todo list* which is one of the feature of the *Kintro* app. \
The base design of this feature is a nano-redux store which is kind of an advanced design.

Obviously simpler things are possible. \
To look at a simple algorythm sharing design there is the counter feature.

First let's look at the libraries I used to develop the shared module. \
We start by setting up *Koin*, a dependency injection framework for Kotlin and use it to inject the required components. \
Those are JSON serializer (kotlinx.serialization), HTTP client (Ktor), REST client, data repository, redux state container.

```kotlin
fun shared() = module {
    single { createJson() }
    single { createHttpClient(get()) }
    single { TodosApi(get()) }
    single { TodosRepository() }
    single<Store<TodosState, TodosAction, TodosSideEffect>> { TodosStore() }
}
```
We need a serializable class to serve as our data model. \
The class is annoted **@Serializable** in order to deserialize Ktor responses. \
Note that the class is also annotated **@JsExport** in order to be usable from JavaScript client code (+TypeScript definition)

```kotlin
@JsExport
@Serializable
data class Todo(
    val id: Int,
    val userId: Int,
    var title: String,
    var completed: Boolean
)
{
    fun toggleCompleted() {
        completed = !completed
 }
 ```

The REST client implementation is very simple thanks to Ktor. (v1 here)

```kotlin
internal class TodosApi(private val client: HttpClient) : KoinComponent {
    suspend fun fetchTodos() = client.get<List<Todo>>(TODOS_PATH)
    suspend fun fetchTodo(id: Int) = client.get<Todo>("$TODOS_PATH/${id}")

    companion object {
        const val TODOS_PATH = "/todos"
    }
}
```

The repository currently doesn't do much but it would be the place to setup local persistency logic. \
Note how it implements KoinComponent which enables us to inject the REST API client. \
**@Throws(Exception::class)** enables conversion of Kotlin exceptions to *NSError*.

```kotlin
internal class TodosRepository() : KoinComponent {
    private val api : TodosApi by inject()

    @Throws(Exception::class)
    suspend fun getTodos() = api.fetchTodos().take(20)

    @Throws(Exception::class)
    suspend fun getTodo(id: Int) = api.fetchTodo(id)
}
```

Now let's look at the state shape & dispatchable actions. \
We are representing the loading, current filter and items collection. \
With those we can represent the UI as a pure function of state on all platforms.

```kotlin
enum class TodosFilter(val completed: Boolean) {
    TODO(false),
    DONE(true)
}

data class TodosState(
    val todos: List<Todo>,
    val loading: Boolean,
    val filter: TodosFilter
) : RState

sealed class TodosAction : Action {
    object Load : TodosAction()
    data class Filter(val filter: TodosFilter) : TodosAction()
    data class Data(val todos: List<Todo>) : TodosAction()
    data class Loading(val loading: Boolean) : TodosAction()
    data class Error(val error: Exception) : TodosAction()
}
```

## Android integration with Jetpack Compose

[couter-android-1](/)!
[todos-android-1](/)!

I have choosen to keep using Android ViewModel singled by Koin, the injection framework which is also used in the MPP module.

```kotlin
val appModule = module {
    viewModel { TodosViewModel(get()) }
}

// application's onCreate callback
initKoin() {
    androidContext(this@KintroApplication)
    modules(appModule)
}

class TodosViewModel(todosStore: Store<TodosState, TodosAction, TodosSideEffect>) : ViewModel() {
    val store = todosStore
    val observer = todosStore.observeState()
    val sideEffect = todosStore.observeSideEffect()
}
```

Here is how we inject the view model in our composable functions with Koin DSL.

```kotlin
@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodosViewModel = getViewModel()
) {}
```

To use the state in our composable the magic happens with collectAsState from Flow API. \
If you don't intend to do MPP you can straight use jetpack compose mutableStateOf or Android MutableLiveData API.

```kotlin
val state = viewModel.observer.collectAsState()
```

Now we can dispatch actions inside our composables like loading the data and let Compose handle the data update cycles.

```kotlin
LaunchedEffect(eventId) {
    viewModel.store.dispatch(TodosAction.Load)
}

if (state.value.loading) {
    TodosLoading()
} else {
    TodosScreenList(todos = TodosSelector.filteredTodos(state.value))
}
```

## iOS integration with SwiftUI

[couter-ios-1](/)!
[todos-ios-1](/)!

With SwiftUI we can leverage ObservableObject and the @Published property wrapper to react to state changes. \
Also note that more advanced designs are possible, notably usage with Swift 5.5 async/await and RxSwift. \
Posts about Flow usage in Swift exist on the web.

```swift
class ObservableTodosStore: ObservableObject {
    let store = TodosStore()
    @Published private(set) var state = TodosStore.Companion().getInitialState()
    var stateWatcher : Closeable?
    
    init() {
        stateWatcher = self.store.watchState().watch(block: { [weak self] state in
            self?.state = state
        })
    }
    
    public func dispatch(_ action: TodosAction) {
        store.dispatch(action: action)
    }
    
    deinit {
        stateWatcher?.close()
        sideEffectWatcher?.close()
    }
}
```

The observable object is wrapped with **@StateObject** which enables our content view to own it and is passed down the view hierarchy using the **.environmentObject** modifier like so.

```swift
struct ContentView: View {
    @StateObject var todosStore = ObservableTodosStore()
    
    var body: some View {
        MainTabbedView()
            .environmentObject(todosStore) 
    }
}
```

Now we can use observedObject to expose the store and its state inside any view struct.

```swift
struct TodosView: View {
    @EnvironmentObject var store : ObservableTodosStore
    
    var body: some View {
        NavigationView {
            List {
                  ForEach(TodosSelector().filteredTodos(state: store.state), id: \.id) { t in
                      TodoListItem(item: t)
                  }
            }
            .navigationTitle("Todos")
        }
        .onAppear() {
            if !isPreview { store.dispatch(TodosAction.Load()) }
        }
    }
}
```

## Web integration with React.js

In the shared module I have used the Kotlin/JS IR compiler (intermadiate representation) to create a commonjs module usable in the React.js app.

```kotlin
js(IR) {
    useCommonJs()
    browser {
        webpackTask {
            output.libraryTarget = "commonjs2"
        }
    }
    binaries.executable()
}
```

The make the nano-redux store work with JS I encountered a few limitations among which is the impossibility to export sealed classes. \
In order to workaround those limitation I have basically created a view model exporting what is exportable. So here is the trick (as per Kotlin 1.5.30).

```kotlin
@JsExport
class TodosStateJsWrapper(
    val loading : Boolean,
    val todos : Array<Todo>,
    val filter: TodosFilter,
)

fun TodosState.toJsObject() = TodosStateJsWrapper(
    loading,
    todos.toTypedArray(),
    filter
)

@JsExport
object Main {

    private val mainScope = MainScope()
    private val store = TodosStore()

    @Suppress("unused")
    fun cancel() {
        mainScope.cancel()
    }

    @Suppress("unused")
    fun loadTodos() {
        store.dispatch(TodosAction.Load)
    }

    @Suppress("unused")
    fun observeStore(callback: (state: TodosStateJsWrapper) -> Unit) {
        mainScope.launch {
            store.observeState().collect {
                callback(it.toJsObject())
            }
        }
    }
}
```

Now that we have exported a view-model object to JavaScript we need to initialize Koin just like for iOS and Android in order to enable dependencies injection in the MPP module. \
Here I have simply created a main fun that does just that.

```kotlin
fun main() {
    console.log("init Koin context")
    initKoin()
}
```

Now we can export all this to a commonjs2 module. \
To do so I used the following tasks and a custom script to move the generated bundle and TypeScript definition file to a directory from which I install it to the React app with NPM or Yarn.

```shell
./gradlew shared:compileProductionExecutableKotlinJs
./gradlew shared:jsPublicPackageJson
```

Here is the React app package.json

```json
"dependencies": {
  "kintro-shared": "file:../shared/build/dist",
  "react": "^17.0.2",
  "react-dom": "^17.0.2",
}
```

I created a React Context which will be used to access the MPP module. \
It will also make it more friendly by referencing the fully qualified package name.

```javascript
const SharedContext = createContext()
const useSharedContext = () => useContext(SharedContext)

function SharedProvider({ children }) {
  const shared = Shared.dev.ybonnetain.kintro.Main

  return (
    <SharedContext.Provider value={{ shared }}>
      {children}
    </SharedContext.Provider>
  )
}
```

Finally, we can use the multiplatform library in a component like that.

```javascript
function Todos() {
  const { shared } = useSharedContext()

  useEffect(() => {
    shared.loadTodos()
    shared.observeStore(onStateChange)
    return () => shared.cancel()
  }, [shared]);
  
  // .. render
}
```

## That's it

With MPP libraries you can share algorythms, business logic, validation rules, data access and persistency logic for iOS / Android and web browsers.









