import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

import dev.ybonnetain.kintro.di.initKoin
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosStore
import dev.ybonnetain.kintro.store.UsersAction
import dev.ybonnetain.kintro.store.UsersStore


// https://discuss.kotlinlang.org/t/kotlin-js-1-4-how-to-output-a-npm-package/19128
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.js/-js-export/

object Shared : KoinComponent {
    val mainScope = MainScope()

    val store : TodosStore
    val load = TodosAction.Load

    val dispatch = :: _dispatch
    val observe = :: _observe

    init {
        initKoin()
        store = get()
    }

    fun _dispatch(action: TodosAction) {
        store.dispatch(action)
    }

    fun _observe(callback: (state: Any) -> Unit) { // UsersState , Array<User>
        mainScope.launch {
            store.observeState().collect {
                callback(it)
            }
        }
    }
}

fun main() {
    // make the shared module available directly after main invocation
    console.log("Shared main IIFE")
    Shared
}
