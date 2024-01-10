package com.example.food_recept.data.mapper.food

import com.example.food_recept.data.dto.FoodByCategoryDto
import com.example.food_recept.domain.model.FoodByCategoryModel

fun FoodByCategoryDto.toDomain(): FoodByCategoryModel{
    return FoodByCategoryModel(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}