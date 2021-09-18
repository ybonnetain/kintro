//import kotlinx.coroutines.InternalCoroutinesApi
import dev.ybonnetain.kintro.di.initKoin
import dev.ybonnetain.kintro.models.User
import dev.ybonnetain.kintro.store.UsersAction
import dev.ybonnetain.kintro.store.UsersState
import dev.ybonnetain.kintro.store.UsersStore
//import org.koin.core.KoinApplication.Companion.init
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

// https://discuss.kotlinlang.org/t/kotlin-js-1-4-how-to-output-a-npm-package/19128

// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.js/-js-export/
object Shared : KoinComponent {
    val mainScope = MainScope()

    val store : UsersStore
    val load = UsersAction.Load

    fun _dispatch(action: UsersAction) {
        store.dispatch(action)
    }

    fun _observe(callback: (state: Any) -> Unit) { // UsersState , Array<User>
        mainScope.launch {
            store.observeState().collect {
                callback(it)
            }
        }
    }

    val dispatch = :: _dispatch

    val observe = :: _observe

    init {
        initKoin()
        store = get()
    }
}


//@InternalCoroutinesApi
fun main() {
    console.log("Shared main IIFE")
    Shared

//    app.store.observeState()
//    app.store.dispatch(app.load)
}


