package dev.ybonnetain.kintro

import org.koin.dsl.module

actual fun getPlatform() = "web"

actual fun isDebugBuild() = true // TODO [web] set build type in tool chain

actual fun platformModule() = module {

}
