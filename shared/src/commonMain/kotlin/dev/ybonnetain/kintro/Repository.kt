package dev.ybonnetain.kintro

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Repository() : KoinComponent {
    private val api : Remote by inject()

    @Throws(Exception::class)
    suspend fun getTodos() = api.fetchTodos()

    @Throws(Exception::class)
    suspend fun getTodo(id: Int) = api.fetchTodo(id)
}
