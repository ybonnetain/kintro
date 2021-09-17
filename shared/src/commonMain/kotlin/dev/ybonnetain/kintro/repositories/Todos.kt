package dev.ybonnetain.kintro.repositories

import dev.ybonnetain.kintro.remote.TodosApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class TodosRepository() : KoinComponent {
    private val api : TodosApi by inject()

    @Throws(Exception::class)
    suspend fun getTodos() = api.fetchTodos().take(20)

    @Throws(Exception::class)
    suspend fun getTodo(id: Int) = api.fetchTodo(id)
}
