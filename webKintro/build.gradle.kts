plugins {
    kotlin("js")
    kotlin("plugin.serialization")
}


// debug
repositories {
    jcenter() // everything is still there?
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}




dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(project(":shared"))

    with(Deps.Kotlinx) {
        implementation(htmlJs)
        implementation(coroutinesCore)
    }
}

//kotlin {
//    js(IR) {
//        browser()
//        binaries.executable()
//    }
//}

kotlin {
    js {
        useCommonJs()
        browser()
    }
}