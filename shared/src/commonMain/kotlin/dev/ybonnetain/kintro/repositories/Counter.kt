package dev.ybonnetain.kintro.repositories

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Counter() {

    fun observeCounter(): StateFlow<Int> = count

    // n = term position in fibonacci serie
    // we start with 1st term for seed = (0,1) which is 0
    //
    private var n = 1
    private val seed = Pair(0, 1)
    private val count = MutableStateFlow(0)

    fun incrementCounter() {
        calculateTermForPosition(++n)
    }

    fun decrementCounter() {
        if (n > 1) n--
        calculateTermForPosition(n)
    }

    fun resetCounter() {
        n = 1
        calculateTermForPosition(n)
    }

    private fun calculateTermForPosition(n: Int) {
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
