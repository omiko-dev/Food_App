package com.example.food_recept.data.remote.mapper.food

import com.example.food_recept.data.remote.model.food_by_category.FoodByCategoryDto
import com.example.food_recept.domain.model.FoodByCategoryModel

fun FoodByCategoryDto.toDomain(): FoodByCategoryModel{
    return FoodByCategoryModel(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}