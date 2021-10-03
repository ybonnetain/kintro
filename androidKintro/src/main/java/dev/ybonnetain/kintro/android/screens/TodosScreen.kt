package dev.ybonnetain.kintro.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dev.ybonnetain.kintro.android.styles.KintroTheme

import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosFilter
import org.koin.androidx.compose.getViewModel

@Composable
fun TodosScreen(viewModel: TodosViewModel = getViewModel()) {

    val eventId = 0
    val state = viewModel.observer.collectAsState()

    LaunchedEffect(eventId) {
        // initially this is intended for suspend effects
        // here the dispatch launches on Main scope internally
        viewModel.store.dispatch(TodosAction.Load)
    }

    Column {
        TodosScreenFilter(state.value.filter)
        TodosScreenLoading(state.value.loading)
        TodosScreenList(state.value.todos)
    }

}

@Composable
fun TodosScreenList(todos: List<Todo>) {
    Column {
        todos.forEach { todo ->
            ListRow(todo)
        }
    }
}

@Composable
fun ListRow(todo: Todo) {
    Text(todo.title)
}

@Composable
fun TodosScreenFilter(filter: TodosFilter) {
    Text(filter.toString())
}

@Composable
fun TodosScreenLoading(loading: Boolean) {
    if (loading) {
        Text("loading")
    } else {
        Text("loaded")
    }
}



@Preview(showBackground = true)
@Composable
fun TodosScreenLightPreview() {
    KintroTheme(darkTheme = false) {
        TodosScreen(viewModel = TodosViewModel(TodosStoreMock()))
    }
}

@Preview(showBackground = true)
@Composable
fun TodosScreenDarkPreview() {
    KintroTheme(darkTheme = true) {
        TodosScreen(viewModel = TodosViewModel(TodosStoreMock()))
    }
}
