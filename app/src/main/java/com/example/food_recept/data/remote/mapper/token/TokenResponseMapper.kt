package com.example.food_recept.data.remote.mapper.token

import com.example.food_recept.data.remote.model.token.TokenDto
import com.example.food_recept.domain.response.TokenModelResponse

fun TokenDto.toDomain(): TokenModelResponse{
    return TokenModelResponse(token = token)
}