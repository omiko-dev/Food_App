package com.example.food_recept.presentation.mapper.food

import com.example.food_recept.domain.model.FoodModel
import com.example.food_recept.presentation.model.Food

fun Food.toDomain() =
    FoodModel(
        idMeal = idMeal,
        strArea = country,
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
        strMealThumb = mealImage,
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