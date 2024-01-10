package com.example.food_recept.data.mapper.category

import com.example.food_recept.data.dto.CategoryListDto
import com.example.food_recept.domain.response.CategoryModelResponse

fun CategoryListDto.toDomain(): CategoryModelResponse {
    return CategoryModelResponse(categories = categories.map { it.toDomain() })
}