package dev.ybonnetain.kintro.android

import android.app.Application
import dev.ybonnetain.kintro.appContext
import dev.ybonnetain.kintro.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this

        initKoin() {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}