package dev.ybonnetain.kintro

import co.touchlab.kermit.LogcatLogger
import co.touchlab.kermit.Logger
import org.koin.dsl.module

// I get android context with Koin (see Android's KintroApplication)
// If a context is needed outside of the platform module there something possible
// here -> lateinit var appContext: Context
// android application's onCreate -> appContext = this

actual fun getPlatform() = "android"

actual fun isDebugBuild() : Boolean = BuildConfig.DEBUG

actual fun platformModule() = module {
    single<Logger> { LogcatLogger() }
}
