// https://kotlinlang.org/docs/mpp-dsl-reference.html
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    // define the kotlin native target to be invoked to match the target arch
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64 // available to KT 1.5.30
        else -> ::iosX64
    }

    iosTarget("ios") {}

    // https://kotlinlang.org/docs/native-cocoapods.html
    // When sync this will produce the framework's podspec
    cocoapods {
        framework {
            summary = "the business code"
            homepage = "https://ybonnetain.dev/blog-kotlin-multiplatform"
            baseName = "Shared"
            // (Optional) do a dynamic framework to enable SwiftUI previews,
            // If you use a pod dependency in Gradle project, then ensure that your project's Podfile has use_frameworks!
            isStatic = false
            // embedBitcode(BITCODE) // (Optional) Dependency export

            // below goes the pods dependencies and the deployment target
            ios.deploymentTarget = "14.1"
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

    // TODO: use IR backend ! when using it cannot import common classes in kjs source base
    js {
        browser()
        compilations.all {
            kotlinOptions {
                moduleKind = "commonjs"
            }
        }
    }
//    js(IR) {
//        useCommonJs()
//        browser()
//    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Kotlinx) {
                    implementation(coroutinesCore) // TODO use api() for JS app ?
                    implementation(serializationCore)
                }

                with(Deps.Ktor) {
                    implementation(core)
                    implementation(serialization)
                }

                api(Deps.Koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Ktor.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Deps.Ktor.ios)
            }
        }
        val iosTest by getting
        val jsMain by getting {
            dependencies {
                implementation(Deps.Ktor.js)
            }
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
}
