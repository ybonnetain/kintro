package dev.ybonnetain.kintro

import android.content.Context
import org.koin.dsl.module

// TODO [android] use koin container to get app context instead (see MainApplication to replace)
lateinit var appContext: Context

actual fun getPlatform() = "android"

actual fun isDebugBuild() : Boolean = BuildConfig.DEBUG

actual fun platformModule() = module {

}

