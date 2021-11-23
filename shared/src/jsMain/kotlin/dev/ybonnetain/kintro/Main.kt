package dev.ybonnetain.kintro

import dev.ybonnetain.kintro.di.initKoin
import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.repositories.Counter
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosFilter
import dev.ybonnetain.kintro.store.TodosState
import dev.ybonnetain.kintro.store.TodosStore
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun main() {
    console.log("init Koin context")
    initKoin()
}

@ExperimentalJsExport
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

@ExperimentalJsExport
@JsExport
object Main {

    private val mainScope = MainScope()
    private val counter = Counter()
    private val store = TodosStore()

    @Suppress("unused")
    fun cancel() {
        mainScope.cancel()
    }

    // Counter

    @Suppress("unused")
    fun incrementCounter() {
        counter.incrementCounter()
    }

    @Suppress("unused")
    fun decrementCounter() {
        counter.decrementCounter()
    }

    @Suppress("unused")
    fun resetCounter() {
        counter.resetCounter()
    }

    @Suppress("unused")
    fun observeCounter(callback: (count: Int) -> Unit) {
        mainScope.launch {
            counter.observeCounter().collect {
                callback(it)
            }
        }
    }

    // Todos nano redux

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
