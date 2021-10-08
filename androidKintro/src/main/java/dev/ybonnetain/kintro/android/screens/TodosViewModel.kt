package dev.ybonnetain.kintro.android.screens

import androidx.lifecycle.ViewModel
import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.store.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodosViewModel(todosStore: Store<TodosState, TodosAction, TodosSideEffect>) : ViewModel() {

    val store = todosStore
    val observer = todosStore.observeState()
    val sideEffect = todosStore.observeSideEffect()
}

class TodosStoreMock : Store<TodosState, TodosAction, TodosSideEffect> {
    private val state = MutableStateFlow(getPreviewState())
    private val sideEffect = MutableSharedFlow<TodosSideEffect>()

    override fun observeState(): StateFlow<TodosState> = state
    override fun observeSideEffect(): Flow<TodosSideEffect> = sideEffect
    override fun dispatch(action: TodosAction) {}

    companion object {
        fun getPreviewState() = TodosState(
            todos = listOf(Todo(title = "dummy todo", userId = 1, id = 1, completed = false)),
            loading = false,
            filter = TodosFilter.TODO
        )
    }
}
