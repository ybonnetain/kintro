package dev.ybonnetain.kintro.di

import co.touchlab.kermit.Kermit
import dev.ybonnetain.kintro.Configuration
import dev.ybonnetain.kintro.isDebugBuild
import dev.ybonnetain.kintro.mocks.MockDispatcher
import dev.ybonnetain.kintro.platformModule
import dev.ybonnetain.kintro.remote.TodosApi
import dev.ybonnetain.kintro.remote.UsersApi
import dev.ybonnetain.kintro.repositories.Counter
import dev.ybonnetain.kintro.repositories.TodosRepository
import dev.ybonnetain.kintro.repositories.UsersRepository
import dev.ybonnetain.kintro.store.TodosStore
import dev.ybonnetain.kintro.store.UsersStore
import kotlinx.serialization.json.Json

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.*

// Koin init
// in darwin / js runtime (no app module)

fun initKoin() = initKoin() {}

// Koin init
// in android runtime (app module)
// platformModule is a platform specific module extension (expect)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(shared(), platformModule())
}

// modules map
// Everything we need to inject in the KMM shared module

fun shared() = module {
    single { Kermit(logger = get()) }
    single { createJson() }

    if (Configuration.mock && isDebugBuild()) {
        single { createHttpMockClient(get()) }
    } else {
        single { createHttpClient(get()) }
    }

    single { Counter() }

    single { TodosApi(get()) }
    single { TodosRepository() }
    single { TodosStore() }

    single { UsersApi(get()) }
    single { UsersRepository() }
    single { UsersStore() }
}

// Json serializer for HTTP responses
// kotlinx-serialization

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

// HTTP mock client
//

fun createHttpMockClient(json: Json) = HttpClient(MockEngine) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
    engine {
        addHandler { request ->
            fun handler(content: String, status: HttpStatusCode, headers: Headers) = respond(content, status, headers)
            MockDispatcher.dispatch(request, ::handler)
        }
    }
}


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