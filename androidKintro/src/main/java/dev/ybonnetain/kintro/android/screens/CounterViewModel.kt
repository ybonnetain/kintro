package dev.ybonnetain.kintro.android.screens

import androidx.lifecycle.ViewModel
import dev.ybonnetain.kintro.repositories.Counter

class CounterViewModel(private val repository: Counter) : ViewModel() {

    var observer = repository.observeCounter()

    fun increment() { repository.incrementCounter() }
}
