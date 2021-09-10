object Versions {
    const val kotlin = "1.5.21"
    const val kotlinCoroutines = "1.5.1-native-mt"
    const val koin = "3.1.2"
    const val ktor = "1.6.3"
    const val kotlinxSerialization = "1.2.2"
    const val compose = "1.0.2"
    const val accompanist = "0.17.0"
    const val lifecycle = "2.2.0"
}

object Deps {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
    }

    object Ktor {
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val js = "io.ktor:ktor-client-js:${Versions.ktor}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Androidx {
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

}