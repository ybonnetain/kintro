package dev.ybonnetain.kintro.remote

import dev.ybonnetain.kintro.models.Todo
import dev.ybonnetain.kintro.models.User
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

internal class TodosApi(private val client: HttpClient) : KoinComponent {
    suspend fun fetchTodos() = client.get<List<Todo>>(TODOS_PATH)
    suspend fun fetchTodo(id: Int) = client.get<Todo>("$TODOS_PATH/${id}")

    suspend fun fetchUsers() = client.get<List<User>>(USERS_PATH)
    suspend fun fetchUser(id: Int) = client.get<User>("$USERS_PATH/${id}")

    companion object {
        const val TODOS_PATH = "/todos"
        const val USERS_PATH = "/users"
    }
}