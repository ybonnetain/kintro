package dev.ybonnetain.kintro

import org.koin.dsl.module

actual fun getPlatform() = "ios"

actual fun isDebugBuild() = Platform.isDebugBinary

actual fun platformModule() = module {

}
