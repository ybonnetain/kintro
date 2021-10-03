package dev.ybonnetain.kintro.android.screens

import androidx.lifecycle.ViewModel
import dev.ybonnetain.kintro.repositories.ICounter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Interface enable usage of repository mock in previews
//
class CounterMock : ICounter {
    override fun observeCounter() : StateFlow<Int> = MutableStateFlow(0)
    override fun incrementCounter() {}
    override fun decrementCounter()  {}
    override fun resetCounter() {}
}

// On previews we will give it a counter mock
// else we give it the real world implementation
//
class CounterViewModel(private val repository: ICounter) : ViewModel() {
    var observer = repository.observeCounter()

    fun increment() { repository.incrementCounter() }
    fun decrement() { repository.decrementCounter() }
    fun reset() { repository.resetCounter() }
}
