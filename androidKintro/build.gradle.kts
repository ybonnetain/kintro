plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.18.0")

    with(Deps.Androidx) {
        implementation(activityCompose)
        implementation(lifecycleViewModel)
        implementation(lifecycleRuntime)
    }

    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    with(Deps.Compose) {
        implementation(ui)
        implementation(uiGraphics)
        implementation(foundationLayout)
        implementation(material)
        implementation(navigation)
        implementation(uiTooling)
    }
}

android {
    compileSdk = Versions.androidCompileSdk

    defaultConfig {
        applicationId = "dev.ybonnetain.kintro.android"
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}