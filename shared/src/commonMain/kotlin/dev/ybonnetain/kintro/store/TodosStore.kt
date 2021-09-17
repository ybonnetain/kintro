package dev.ybonnetain.kintro.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.repositories.TodosRepository

data class TodosState(
    val todos: List<Todo>,
    val loading: Boolean,
) : RState

sealed class TodosAction : Action {
    object Load : TodosAction()
    data class Data(val todos: List<Todo>) : TodosAction()
    data class Loading(val loading: Boolean) : TodosAction()
    data class Error(val error: Exception) : TodosAction()
}

sealed class TodosSideEffect : Effect {
    data class Error(val error: Exception) : TodosSideEffect()
}

class TodosStore : Store<TodosState, TodosAction, TodosSideEffect>,
        CoroutineScope by CoroutineScope(Dispatchers.Main),
            KoinComponent
{
    private val repository: TodosRepository by inject()
    private val state = MutableStateFlow(TodosState(todos = emptyList(), loading = false))
    private val sideEffect = MutableSharedFlow<TodosSideEffect>()

    override fun observeState(): StateFlow<TodosState> = state
    override fun observeSideEffect(): Flow<TodosSideEffect> = sideEffect

    override fun dispatch(action: TodosAction) {
        val oldState = state.value

        val nextState = when(action) {
            is TodosAction.Load -> {
                launch { loadTodos() } // side effect ..
                oldState
            }
            is TodosAction.Data -> {
                TodosState(loading = false, todos = action.todos)
            }
            is TodosAction.Loading -> {
                TodosState(loading = action.loading, todos = oldState.todos)
            }
            is TodosAction.Error -> {
                launch { sideEffect.emit(TodosSideEffect.Error(action.error)) }
                oldState
            }
        }

        if (nextState != oldState) {
            state.value = nextState
        }
    }

    private suspend fun loadTodos() {
        try {
            dispatch(TodosAction.Loading(true))
            dispatch(TodosAction.Data(repository.getTodos()))
        } catch (e: Exception) {
            dispatch(TodosAction.Error(e))
        } finally {
            dispatch(TodosAction.Loading(false))
        }
    }
}
