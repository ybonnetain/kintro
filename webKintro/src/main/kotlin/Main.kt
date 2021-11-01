package dev.ybonnetain.kintro.webkintro

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*
import kotlin.js.Promise

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

import dev.ybonnetain.kintro.di.initKoin
import dev.ybonnetain.kintro.repositories.Counter
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosStore

// Using Kotlin code from JavaScript
// https://kotlinlang.org/docs/js-to-kotlin-interop.html#package-structure
// https://kotlinlang.org/docs/js-overview.html#use-cases-for-kotlin-js
// https://discuss.kotlinlang.org/t/consume-kotlin-multiplatform-library-in-vanilla-js-web-app/22152/2
// https://kotlinlang.org/docs/js-modules.html#apply-jsmodule-to-packages
// https://stackoverflow.com/questions/62372119/kotlin-javascript-bundle-for-usage-in-react-app

@JsExport
fun coucou() = "Hello"



//object Shared : KoinComponent {

//    val mainScope = MainScope()
//
//    // store api
//    //
//    // all `todos` related functionalities in terms of state management, side effect
//    // note: is injected with koin in initializer
//
//    val store : TodosStore
//
//    init {
//        initKoin()
//        store = get()
//    }
//
//    // counter api
//    //
//
//    val counter = Counter()
//    val observeCounter = :: _observeCounter
//
//    fun _observeCounter(callback: (count: Int) -> Unit) {
//        mainScope.launch {
//            counter.observeCounter().collect {
//                callback(it)
//            }
//        }
//    }
//
//
//
//    // -> sealed classed can only have private or protected constructor
//    // so we need to expose required actions here
//
//    val fetchTodos = TodosAction.Load
//
//    // make the dispatch & observe functions available to js
//    // this way I can avoid using @JsExport
//    // which complains about non-exportable super type and properties
//    // that being said @JsExport works just fine
//
//    val dispatch = :: _dispatch
//    val observe = :: _observe
//
//    fun _dispatch(action: TodosAction) {
//        store.dispatch(action)
//    }
//    fun _observe(callback: (state: Any) -> Unit) { // UsersState , Array<User>
//        mainScope.launch {
//            store.observeState().collect {
//                callback(it)
//            }
//        }
//    }
//    //
//
//    // It is currently prohibited to export `extension properties` so fun is written here
//    // We would prefer to write a class extension to get the promise
//    // TODO: stay tuned for kotlin 1.6
//    private suspend fun tick() : String {
//        delay(5000)
//        return "tack"
//    }
//
//    @DelicateCoroutinesApi
//    val tickAsync = :: _tickAsync
//
//    @DelicateCoroutinesApi
//    fun _tickAsync() : Promise<String> = GlobalScope.promise {
//        tick()
//    }
    //
//}
//
//fun main() {
//    // make the shared module available directly after main invocation
//    console.log("Shared main IIFE")
//    Shared
//}
