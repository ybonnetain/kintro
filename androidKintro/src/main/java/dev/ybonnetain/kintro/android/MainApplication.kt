package dev.ybonnetain.kintro.android

import android.app.Application
import dev.ybonnetain.kintro.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin() {
            modules(appModule)
        }
    }
}