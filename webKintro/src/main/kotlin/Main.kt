package dev.ybonnetain.kintro.webkintro

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*

import dev.ybonnetain.kintro.di.initKoin
import dev.ybonnetain.kintro.repositories.Counter
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosState
import dev.ybonnetain.kintro.store.TodosStore

// Using Kotlin code from JavaScript :
//

// at the moment I reexport all needed declarations here
// TODO I should look at exporting the shared MPP module directly

// for information, declarations annotated w/ @JsExport gets
// defined in the ts definition file and the names to be used are the original names

// After exported as a cjs module we can use it like below ..
// then have a look at the generated ts definition file for API usage
//
// import Shared from 'shared' <- I have also named the module Shared (see app's package json)
// const shared = Shared.dev.ybonnetain.kintro.webkintro.Shared <- top level export in this file

// how to "resolve" suspend fun in JS Promise ..
// import kotlin.js.Promise
// @DelicateCoroutinesApi
//  fun _tickAsync() : Promise<String> = GlobalScope.promise {
//    someSuspendEffect()
//  }

fun main() {
    console.log("init koin context ...") // so internal injection in shared module is possible
    initKoin()
}

@ExperimentalJsExport
@JsExport
object Shared {

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
    fun observeStore(callback: (state: TodosState) -> Unit) {
        mainScope.launch {
            store.observeState().collect {
                callback(it)
            }
        }
    }
}
