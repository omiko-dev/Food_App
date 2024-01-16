package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.model.FoodModel
import com.example.food_recept.presentation.model.Food

fun FoodModel.toPresenter(): Food {
    return Food(
        idMeal = idMeal,
        country = strArea,
        category = strCategory,
        ingredients = listOf(
            strIngredient1,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
        ),
        instructions = strInstructions,
        meal = strMeal,
        mealImage = strMealThumb,
        measures = listOf(
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            strMeasure5,
            strMeasure6,
            strMeasure7,
            strMeasure8,
            strMeasure9,
        ),
        source = strSource,
        youtube = strYoutube
    )
}