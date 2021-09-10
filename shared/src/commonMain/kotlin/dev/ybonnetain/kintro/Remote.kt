package dev.ybonnetain.kintro

import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

internal class Remote(private val client: HttpClient) : KoinComponent {
    suspend fun getStuff() = client.get<String>(STUFF_PATH)

    companion object {
        const val STUFF_PATH = "/stuff"
    }
}
