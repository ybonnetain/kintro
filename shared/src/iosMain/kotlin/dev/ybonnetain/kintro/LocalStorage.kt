package dev.ybonnetain.kintro

import platform.Foundation.NSUserDefaults
import platform.Foundation.valueForKey

actual class LocalStorage {

    actual fun persist(key: String, value: String) {
        NSUserDefaults.standardUserDefaults.setObject(value, key)
    }

    actual fun retrieve(key: String, default: String) =
        NSUserDefaults.standardUserDefaults.valueForKey(key) as? String ?: default
}
