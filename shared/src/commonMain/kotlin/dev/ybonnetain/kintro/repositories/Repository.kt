package dev.ybonnetain.kintro.repositories

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Repository() : KoinComponent {
    private val api : Remote by inject()

    var n = 0
    // last fibonacci term in the sequence from 0 to n terms
    fun incrementCounter() : Int {
        n++
        return generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) })
            .map { it.first }
            .take(n)
            .toList()
            .last()
    }

    @Throws(Exception::class)
    suspend fun getTodos() = api.fetchTodos()

    @Throws(Exception::class)
    suspend fun getTodo(id: Int) = api.fetchTodo(id)
}
