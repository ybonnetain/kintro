package dev.ybonnetain.kintro

import org.koin.core.module.Module

expect  fun getPlatform(): String

expect fun isDebugBuild() : Boolean

expect fun platformModule(): Module
