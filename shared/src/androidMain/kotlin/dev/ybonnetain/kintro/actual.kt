package dev.ybonnetain.kintro

import android.content.Context
import org.koin.dsl.module

lateinit var appContext: Context

actual fun getPlatform() = "android"

actual fun platformModule() = module {

}

