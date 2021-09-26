package dev.ybonnetain.kintro

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import dev.ybonnetain.kintro.repositories.Counter
import dev.ybonnetain.kintro.store.TodosStore
import dev.ybonnetain.kintro.store.UsersStore

// from jetbrain, this enable Flow usage in SwuifUI publishers
// https://github.com/Kotlin/kmm-production-sample/blob/635ae763f7e666d25827f8e221672020063e617f/shared/src/iosMain/kotlin/com/github/jetbrains/rssreader/core/CFlow.kt

fun interface Closeable {
    fun close()
}

class CFlow<T: Any> internal constructor(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return Closeable { job.cancel() }
    }
}

internal fun <T: Any> Flow<T>.wrap(): CFlow<T> = CFlow(this)

fun Counter.watchCount() = observeCounter().wrap()

fun TodosStore.watchState() = observeState().wrap()
fun TodosStore.watchSideEffect() = observeSideEffect().wrap()

fun UsersStore.watchState() = observeState().wrap()
fun UsersStore.watchSideEffect() = observeSideEffect().wrap()
