package dev.ybonnetain.kintro

import org.koin.core.module.Module

expect  fun getPlatform(): String

expect fun isDebugBuild() : Boolean

expect fun platformModule(): Module

expect class LocalStorage() {
    fun persist(key: String, value: String)
    fun retrieve(key: String, default: String) : String
}
