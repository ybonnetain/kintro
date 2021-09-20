package dev.ybonnetain.kintro

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual fun getPlatform() = "ios"

actual fun isDebugBuild() = Platform.isDebugBinary

actual fun platformModule() = module {
    single<Logger> { NSLogLogger() }
}
