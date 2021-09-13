package dev.ybonnetain.kintro.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import dev.ybonnetain.kintro.appContext
import dev.ybonnetain.kintro.di.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this // TODO: use context passed in Koin

        initKoin() {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}