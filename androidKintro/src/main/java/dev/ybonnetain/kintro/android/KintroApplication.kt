package dev.ybonnetain.kintro.android

import android.app.Application
import co.touchlab.kermit.Kermit
import org.koin.android.ext.koin.androidContext
import dev.ybonnetain.kintro.appContext
import dev.ybonnetain.kintro.di.initKoin
import org.koin.android.ext.android.inject

class KintroApplication : Application() {
    private val logger: Kermit by inject()

    override fun onCreate() {
        super.onCreate()
        appContext = this // TODO: use context passed in Koin

        initKoin() {
            androidContext(this@KintroApplication)
            modules(appModule)
        }

        logger.d { "Kintro application created" }
    }
}