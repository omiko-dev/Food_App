package com.example.food_recept.data.remote.mapper.register

import com.example.food_recept.data.remote.model.register.RegisterRequestDto
import com.example.food_recept.domain.request.RegisterModelRequest

fun RegisterModelRequest.toData(): RegisterRequestDto {
    return RegisterRequestDto(
        name = name,
        email = email,
        password = password
    )
}