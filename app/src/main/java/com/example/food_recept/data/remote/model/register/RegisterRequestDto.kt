package com.example.food_recept.data.remote.model.register

import com.squareup.moshi.Json

data class RegisterRequestDto(
    val name: String,
    val email: String,
    @Json(name = "passwordHash")
    val password: String
)