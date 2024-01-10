package com.example.food_recept.data.mapper.food

import com.example.food_recept.data.dto.FoodModelResponseDto
import com.example.food_recept.domain.response.FoodModelResponse

fun FoodModelResponseDto.toDomain(): FoodModelResponse{
    return FoodModelResponse(meals.map { it.toDomain() })
}