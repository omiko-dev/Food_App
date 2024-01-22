package com.example.food_recept.data.local.mapper

import com.example.food_recept.data.local.entity.FoodEntity
import com.example.food_recept.domain.model.FoodModel

fun FoodModel.toData() =
    FoodEntity(
        foodId = idMeal.toInt(),
        area = strArea,
        category = strCategory,
        imageSource = strMealThumb,
        instructions = strInstructions,
        meal = strMeal,
        mealThumb = strMealThumb,
        source = strSource ?: "",
        tags = "",
        youtube = strYoutube
    )