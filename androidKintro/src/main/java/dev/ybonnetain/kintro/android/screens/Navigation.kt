package dev.ybonnetain.kintro.android.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val title: String, val route: String) {
    object Counter : Screen("Counter", "counter")
    object Todos : Screen("Todos", "todos")
    object TodoDetail: Screen("Todo", "todoDetail")
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
)

val navItems = listOf(
    BottomNavItem(
        Screen.Counter.title,
        Icons.Default.AddCircle,
        "Counter"
    ),
    BottomNavItem(
        Screen.Todos.title,
        Icons.Default.CheckCircle,
        "Todos"
    )
)

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Counter.route) {
        composable(Screen.Counter.route) {
            CounterScreen()
        }
        composable(Screen.Todos.route) {
            TodosScreen(navController)
        }
        composable(Screen.TodoDetail.route) {
            TodoDetailScreen(navController)
        }
    }
}
