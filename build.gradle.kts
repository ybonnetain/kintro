buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        // Gradle version MANAGED by AS
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")

        with(Deps.Gradle) {
            classpath(kotlin)
            classpath(kotlinSerialization)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}