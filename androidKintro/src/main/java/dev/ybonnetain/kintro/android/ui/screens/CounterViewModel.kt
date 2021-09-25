package dev.ybonnetain.kintro.android.ui.screens

import androidx.lifecycle.ViewModel
import dev.ybonnetain.kintro.repositories.Counter

class CounterViewModel() : ViewModel() {
    private val repository: Counter = Counter()

    fun increment() : Int = repository.incrementCounter()
}
