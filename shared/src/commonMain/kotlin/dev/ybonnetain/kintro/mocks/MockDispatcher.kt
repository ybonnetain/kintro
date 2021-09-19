package dev.ybonnetain.kintro.mocks

import dev.ybonnetain.kintro.remote.TodosApi
import dev.ybonnetain.kintro.remote.UsersApi
import io.ktor.client.request.*
import io.ktor.http.*

object MockDispatcher {

    private val ok = HttpStatusCode.OK
    private val notFound = HttpStatusCode.NotFound
    private val json = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
    private val text = headersOf("Content-Type" to listOf(ContentType.Text.Plain.toString()))

    private fun HttpRequestData.has(path: String) :  Boolean {
        return this.url.fullPath.contains(path)
    }

    fun dispatch(
        request: HttpRequestData,
        handler: (content: String, status: HttpStatusCode, headers: Headers) -> HttpResponseData)
            : HttpResponseData {

        return when {
            request.has(TodosApi.TODOS_PATH) -> handler(TodosMock(), ok, json)
            request.has(UsersApi.USERS_PATH) -> handler(UsersMock(), ok, json)

            else -> handler("mock not implemented yet", notFound, text)
        }
    }
}