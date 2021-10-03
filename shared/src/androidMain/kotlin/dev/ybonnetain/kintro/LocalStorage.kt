package dev.ybonnetain.kintro

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class LocalStorage : KoinComponent {

    private val context : Context by inject()

    actual fun persist(key: String, value: String) {
        with(getPrefs().edit()) {
            putString(key, value)
            apply()
        }
    }

    actual fun retrieve(key: String, default: String) = getPrefs().getString(key, default) ?: default

    private fun getPrefs() = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)

    companion object {
        const val sharedPreferences = "dev.ybonnetain.kintro.preferences"
    }
}
