package dev.ybonnetain.kintro.models

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
@Serializable
data class Todo(
    val id: Int,
    val userId: Int,
    var title: String,
    var completed: Boolean
)
{
    fun toggleCompleted() {
        completed = !completed
    }
}
