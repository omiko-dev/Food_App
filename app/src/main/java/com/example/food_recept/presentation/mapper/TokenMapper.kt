package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.response.TokenModelResponse
import com.example.food_recept.presentation.model.Token

fun TokenModelResponse.toPresenter(): Token {
    return Token(token = token)
}