package dev.ybonnetain.kintro

import kotlinx.browser.localStorage

actual class LocalStorage {

    actual fun persist(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    actual fun retrieve(key: String, default: String) = localStorage.getItem(key) ?: default
}
