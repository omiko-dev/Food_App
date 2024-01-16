package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.model.FoodByCategoryModel
import com.example.food_recept.presentation.model.FoodByCategory

fun FoodByCategoryModel.toPresenter(): FoodByCategory{
    return FoodByCategory(
        mealId = idMeal,
        meal = strMeal,
        mealImage = strMealThumb
    )
}