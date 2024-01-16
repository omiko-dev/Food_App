package com.example.food_recept.data.mapper.food

import com.example.food_recept.data.model.food_by_category.FoodByCategoryDto
import com.example.food_recept.domain.model.FoodByCategoryModel

fun FoodByCategoryDto.toDomain(): FoodByCategoryModel{
    return FoodByCategoryModel(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}