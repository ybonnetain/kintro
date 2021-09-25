package dev.ybonnetain.kintro.android

import dev.ybonnetain.kintro.android.ui.screens.CounterViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { CounterViewModel() }
}
