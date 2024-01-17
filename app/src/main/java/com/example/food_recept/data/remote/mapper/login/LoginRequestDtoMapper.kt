package com.example.food_recept.data.remote.mapper.login

import com.example.food_recept.data.remote.model.login.LoginRequestDto
import com.example.food_recept.domain.request.LoginModelRequest

fun LoginModelRequest.toData(): LoginRequestDto {
    return LoginRequestDto(email = email, password = password)
}