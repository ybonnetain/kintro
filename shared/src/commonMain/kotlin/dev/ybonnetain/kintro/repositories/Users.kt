package dev.ybonnetain.kintro.repositories

import dev.ybonnetain.kintro.remote.UsersApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class UsersRepository : KoinComponent {
    private val api : UsersApi by inject()

    @Throws(Exception::class)
    suspend fun getUsers() = api.fetchUsers()

    @Throws(Exception::class)
    suspend fun getUser(id: Int) = api.fetchUser(id)
}
