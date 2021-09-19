package dev.ybonnetain.kintro.repositories

import kotlinx.coroutines.delay

class Counter() {

    var n = 0

    // returns n-th fibonacci term with seed (0,1)
    //
    fun incrementCounter() : Int {
        n++
        return generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) })
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
