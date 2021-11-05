package dev.ybonnetain.kintro.repositories

import dev.ybonnetain.kintro.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ICounter {
    fun observeCounter() : StateFlow<Int>
    fun incrementCounter()
    fun decrementCounter()
    fun resetCounter()
}

class Counter : KoinComponent, ICounter {

    override fun observeCounter(): StateFlow<Int> = count

    // n = term position in fibonacci serie
    // we start with 1st term for seed = (0,1) which is 0
    //
    private val storage: LocalStorage by inject()
    private var n = storage.retrieve(PERSISTENCE_KEY, "1").toInt()
    private val seed = Pair(0, 1)
    private val count = MutableStateFlow(fibonacciValueForTerm(n))

    override fun incrementCounter() {
        move(++n)
    }

    override fun decrementCounter() {
        if (n > 1) n--
        move(n)
    }

    override fun resetCounter() {
        n = 1
        move(n)
    }

    private fun move(n: Int) {
        storage.persist(PERSISTENCE_KEY, n.toString())
        count.value = fibonacciValueForTerm(n)
    }

    private fun fibonacciValueForTerm(n: Int) = generateSequence(
        seed, { Pair(it.second, it.first + it.second) })
            .map { it.first }
            .take(n)
            .toList()
            .last()

    companion object {
        const val PERSISTENCE_KEY = "counter_internal_term"
    }
}
