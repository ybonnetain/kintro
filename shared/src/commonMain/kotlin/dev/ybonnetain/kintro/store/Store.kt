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
