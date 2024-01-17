package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.request.LoginModelRequest
import com.example.food_recept.presentation.model.Login

fun Login.toDomain(): LoginModelRequest {
    return LoginModelRequest(
        email = email,
        password = password
    )
}