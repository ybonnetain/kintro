package dev.ybonnetain.kintro.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    var name: String,
    var username: String,
    var email: String
)
