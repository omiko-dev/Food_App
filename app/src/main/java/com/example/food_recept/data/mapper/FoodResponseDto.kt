package com.example.food_recept.data.mapper

import com.example.food_recept.data.dto.FoodResponseDto
import com.example.food_recept.domain.response.FoodResponse

fun FoodResponseDto.toDomain(): FoodResponse{
    return FoodResponse(meals.toDomain())
}