package dev.ybonnetain.kintro.repositories

import dev.ybonnetain.kintro.store.TodosSideEffect
import dev.ybonnetain.kintro.store.TodosState
import dev.ybonnetain.kintro.store.TodosStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Counter() {

    private val count = MutableStateFlow(0)

    fun observeCounter(): StateFlow<Int> = count

    // n = term position in fibonacci serie
    // we start with 1st term for seed = (0,1)
    //
    private var n = 1
    private val seed = Pair(0, 1)

    fun incrementCounter() {
        currentTerm(++n)
    }

    fun decrementCounter() {
        if (n > 1) currentTerm(n--)
    }

    fun resetCounter() {
        n = 1
        currentTerm(n)
    }

    private fun currentTerm(n: Int) {
        count.value = generateSequence(seed, { Pair(it.second, it.first + it.second) })
            .map { it.first }
            .take(n)
            .toList()
            .last()
    }

    suspend fun tick() : String {
        delay(5000)
        return "tack"
    }
}
