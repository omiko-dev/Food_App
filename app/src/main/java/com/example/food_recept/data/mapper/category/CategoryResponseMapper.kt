package com.example.food_recept.data.mapper.category

import com.example.food_recept.data.model.category.CategoryListDto
import com.example.food_recept.domain.response.CategoryModelResponse

fun CategoryListDto.toDomain(): CategoryModelResponse {
    return CategoryModelResponse(categories = categories.map { it.toDomain() })
}