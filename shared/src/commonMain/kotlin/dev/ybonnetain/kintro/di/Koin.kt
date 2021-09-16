package dev.ybonnetain.kintro.di

import dev.ybonnetain.kintro.Configuration
import dev.ybonnetain.kintro.platformModule
import dev.ybonnetain.kintro.remote.TodosApi
import dev.ybonnetain.kintro.repositories.TodosRepository
import dev.ybonnetain.kintro.store.TodosStore
import kotlinx.serialization.json.Json

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.*

// Koin init
// in darwin runtime

fun initKoin() = initKoin() {}

// Koin init
// in android runtime
// because app module declaration, platformModule is a platform specific container (expect.kt)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(shared(), platformModule())
}

// modules map
// Everything we need to inject in the KMM shared module

fun shared() = module {
    single { createJson() }
    single { createHttpClient(get()) }

    single { TodosApi(get()) }
    single { TodosRepository() }
    single { TodosStore() }
}

// Json serializer for HTTP responses
// kotlinx-serialization

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

// HTTP client
// Ktor with android and ios engines configured through the KMM plugin

fun createHttpClient(json: Json) = HttpClient {

    install(HttpTimeout) {
        requestTimeoutMillis = 60_000
        connectTimeoutMillis = 60_000
        socketTimeoutMillis = 60_000
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(DefaultRequest) {
        headers {
            append("x-client-src", "kintro")
        }
        url {
            protocol = if (Configuration.scheme == "http") URLProtocol.HTTP else URLProtocol.HTTPS
            host = Configuration.host
        }
    }
}