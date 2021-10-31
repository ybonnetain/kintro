package dev.ybonnetain.kintro.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment

import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.Scale
import dev.ybonnetain.kintro.android.styles.typography
import dev.ybonnetain.kintro.android.views.SegmentControls
import dev.ybonnetain.kintro.android.views.layout.TopBar
import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosFilter
import dev.ybonnetain.kintro.store.TodosSelector
import org.koin.androidx.compose.getViewModel

@Composable
fun TodosScreen(viewModel: TodosViewModel = getViewModel()) {

    val eventId = 0
    val state = viewModel.observer.collectAsState()

    fun onFilterChange(index: Int) {
        when (index) {
            0 -> viewModel.store.dispatch(TodosAction.Filter(TodosFilter.TODO))
            1 -> viewModel.store.dispatch(TodosAction.Filter(TodosFilter.DONE))
            else -> {}
        }
    }

    LaunchedEffect(eventId) {
        // initially this is intended for suspend effects
        // here the dispatch launches on Main scope internally
        viewModel.store.dispatch(TodosAction.Load)
    }

    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        TopBar("Todos")
        SegmentControls(
            items = enumValues<TodosFilter>().map { v -> v.name },
            currentIndex = TodosFilter.valueOf(state.value.filter.toString()).ordinal,
            callback = ::onFilterChange
        )
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(Scale.base),
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 60.dp)
        ) {
            if (state.value.loading) {
                TodosLoading()
            } else {
                TodosScreenList(TodosSelector.filteredTodos(state.value))
            }
        }
    }
}

@Composable
fun TodosScreenList(todos: List<Todo>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = Scale.base),
    ) {
        itemsIndexed(todos) { i, todo ->
            ListRow(todo)
            if (i != todos.count() - 1) Divider()
        }
    }
}

@Composable
fun ListRow(todo: Todo) {
    Box(modifier = Modifier
        .background(MaterialTheme.colors.surface)
        .fillMaxWidth(1f)
        .padding(Scale.minus1)
    ) {
        Text(
            color = MaterialTheme.colors.onSurface,
            text = todo.title,
            style = typography.subtitle2,
        )
    }
}

@Composable
fun TodosLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
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
