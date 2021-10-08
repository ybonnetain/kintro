package dev.ybonnetain.kintro.android

import dev.ybonnetain.kintro.android.screens.CounterViewModel
import dev.ybonnetain.kintro.android.screens.TodosViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { CounterViewModel(get()) }
    viewModel { TodosViewModel(get()) }
}
