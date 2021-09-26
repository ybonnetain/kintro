object Versions {
    const val kotlin = "1.5.21"
    const val kotlinCoroutines = "1.5.1-native-mt"

    const val kotlinxSerialization = "1.2.2"
    const val kotlinxHtmlJs = "0.7.3"

    const val koin = "3.1.2"
    const val ktor = "1.6.3"
    const val kermit = "0.1.9"

    const val androidMinSdk = 23
    const val androidCompileSdk = 31
    const val androidTargetSdk = androidCompileSdk

    const val lifecycleKtx = "2.4.0-alpha03"
    const val activityCompose = "1.3.1"
    const val compose = "1.1.0-alpha04"
    const val navCompose = "2.4.0-alpha09"
    const val accompanist = "0.18.0"
}

object Deps {
    object Gradle {
        // https://youtrack.jetbrains.com/issue/KT-39833 (1.5.30-M1 + JDK x64) ?
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30" // TODO use Versions when all aligned to 1.5.30
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object Log {
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    }

    object Androidx {
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Kotlinx {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val htmlJs = "org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHtmlJs}"
    }

    object Ktor {
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val js = "io.ktor:ktor-client-js:${Versions.ktor}"
        const val mock = "io.ktor:ktor-client-mock:${Versions.ktor}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
    }
}