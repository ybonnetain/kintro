package dev.ybonnetain.kintro.remote

import dev.ybonnetain.kintro.models.User
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

internal class UsersApi(private val client: HttpClient) : KoinComponent {
    suspend fun fetchUsers() = client.get<List<User>>(USERS_PATH)
    suspend fun fetchUser(id: Int) = client.get<User>("$USERS_PATH/${id}")

    companion object {
        const val USERS_PATH = "/users"
    }
}