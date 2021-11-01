package dev.ybonnetain.kintro.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.tabs.TabLayout

import dev.ybonnetain.kintro.android.styles.KintroTheme
import dev.ybonnetain.kintro.android.styles.Scale
import dev.ybonnetain.kintro.android.styles.typography
import dev.ybonnetain.kintro.android.views.SegmentControls
import dev.ybonnetain.kintro.android.views.layout.OverflowMenu
import dev.ybonnetain.kintro.android.views.layout.TopBar
import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.store.TodosAction
import dev.ybonnetain.kintro.store.TodosFilter
import dev.ybonnetain.kintro.store.TodosSelector
import org.koin.androidx.compose.getViewModel

@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodosViewModel = getViewModel()
) {

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
        TopBar("Todos") {
            OverflowMenu {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text("Readme", style = MaterialTheme.typography.body2)
                }
            }
        }
        TabRow(
            selectedTabIndex = TodosFilter.valueOf(state.value.filter.toString()).ordinal,
            backgroundColor = Color.Yellow,
            contentColor = MaterialTheme.colors.primary,
            divider = {
                TabRowDefaults.Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colors.background
                )
            },
        ) {
            enumValues<TodosFilter>().map { v -> v.name }.forEachIndexed { i, t ->
                Tab(
                    selected = TodosFilter.valueOf(state.value.filter.toString()).ordinal == i,
                    onClick = { onFilterChange(i) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    modifier = Modifier.background(MaterialTheme.colors.background)
                ) {
                    Text(
                        text = t.lowercase().replaceFirstChar { it.uppercase() },
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(horizontal = 0.dp, vertical = Scale.base)
                    )
                }
            }
        }
        Card(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 60.dp)
        ) {
            if (state.value.loading) {
                TodosLoading()
            } else {
                TodosScreenList(
                    todos = TodosSelector.filteredTodos(state.value),
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun TodosScreenList(todos: List<Todo>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = Scale.base),
    ) {
        itemsIndexed(todos) { i, todo ->
            ListRow(todo, navigateToDetail = { navController.navigate("todoDetail") })
            if (i != todos.count() - 1) Divider()
        }
    }
}

@Composable
fun ListRow(todo: Todo, navigateToDetail: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth(1f)
            .padding(Scale.minus1)
            .clickable { navigateToDetail() }
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
    val navController = rememberNavController()
    KintroTheme(darkTheme = false) {
        TodosScreen(navController = navController, viewModel = TodosViewModel(TodosStoreMock()))
    }
}

@Preview(showBackground = true)
@Composable
fun TodosScreenDarkPreview() {
    val navController = rememberNavController()
    KintroTheme(darkTheme = true) {
        TodosScreen(navController = navController, viewModel = TodosViewModel(TodosStoreMock()))
    }
}
