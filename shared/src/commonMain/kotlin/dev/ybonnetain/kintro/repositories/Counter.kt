package dev.ybonnetain.kintro.repositories

class Counter() {

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
}
