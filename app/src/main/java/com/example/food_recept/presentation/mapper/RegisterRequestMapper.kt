package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.request.RegisterModelRequest
import com.example.food_recept.presentation.model.Register

fun Register.toDomain(): RegisterModelRequest {
    return RegisterModelRequest(
        name = name,
        email = email,
        password = password
    )
}