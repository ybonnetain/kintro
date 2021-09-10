plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    with(Deps.Androidx) {
        implementation(lifecycleViewModel)
    }

    with(Deps.Koin) {
        implementation(android)
        implementation(compose)
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "dev.ybonnetain.kintro.android"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}