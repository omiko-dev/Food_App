package com.example.food_recept.data.model.user

data class UserDto (
    val id: Int,
    val name: String,
    val email: String,
    val passwordHash: String
)