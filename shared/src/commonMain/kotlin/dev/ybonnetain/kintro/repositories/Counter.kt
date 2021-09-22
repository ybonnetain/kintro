package dev.ybonnetain.kintro.repositories

import kotlinx.coroutines.delay

class Counter() {

    // n = term position in fibonacci serie
    // we start with 1st term for seed = (0,1)
    //
    private var n = 1
    private val seed = Pair(0, 1)

    fun incrementCounter() : Int = currentTerm(++n)

    fun decrementCounter() : Int {
        if (n > 1) n--
        return currentTerm(n)
    }

    fun resetCounter() : Int {
        n = 1
        return currentTerm(n)
    }

    private fun currentTerm(n: Int) = generateSequence(seed, { Pair(it.second, it.first + it.second) })
        .map { it.first }
        .take(n)
        .toList()
        .last()

    suspend fun tick() : String {
        delay(5000)
        return "tack"
    }
}
