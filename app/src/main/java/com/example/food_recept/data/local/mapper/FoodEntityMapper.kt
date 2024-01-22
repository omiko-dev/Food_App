package com.example.food_recept.data.local.mapper

import com.example.food_recept.data.local.entity.FoodEntity
import com.example.food_recept.domain.model.FoodModel

fun FoodEntity.toDomain(): FoodModel =
    FoodModel(
        idMeal = foodId.toString(),
        strArea = area,
        strCategory = category,
        strIngredient1 = null,
        strIngredient2 = null,
        strIngredient3 = null,
        strIngredient4 = null,
        strIngredient5 = null,
        strIngredient6 = null,
        strIngredient7 = null,
        strIngredient8 = null,
        strIngredient9 = null,
        strInstructions = instructions,
        strMeal = meal,
        strMealThumb = mealThumb,
        strMeasure1 = null,
        strMeasure2 = null,
        strMeasure3 = null,
        strMeasure4 = null,
        strMeasure5 = null,
        strMeasure6 = null,
        strMeasure7 = null,
        strMeasure8 = null,
        strMeasure9 = null,
        strSource = source,
        strYoutube = youtube
    )