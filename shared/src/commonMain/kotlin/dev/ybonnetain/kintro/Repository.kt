package dev.ybonnetain.kintro

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Repository() : KoinComponent {
    private val api : Remote by inject()
}