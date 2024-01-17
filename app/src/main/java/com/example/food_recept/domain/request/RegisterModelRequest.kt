package com.example.food_recept.domain.request

data class RegisterModelRequest (
    val name: String,
    val email: String,
    val password: String
)