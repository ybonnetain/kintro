package dev.ybonnetain.kintro.store

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RState
interface Action
interface Effect

interface Store<S: RState, A: Action, E:Effect> {
    fun observeState(): StateFlow<S>
    fun observeSideEffect(): Flow<E>
    fun dispatch(action: A)
}

// Implementing function interface is prohibited in JavaScript
//class Memoize1<in T, out R>(val f: (T) -> R) : (T) -> R {
//    private val values = mutableMapOf<T, R>()
//    override fun invoke(s: T): R {
//        return values.getOrPut(s, { f(s) })
//    }
//}
//
//fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)
