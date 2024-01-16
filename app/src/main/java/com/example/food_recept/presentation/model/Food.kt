package com.example.food_recept.presentation.model

data class Food(
    val idMeal: String,
    val country: String,
    val category: String,
    var ingredients: List<String?>,
    val instructions: String,
    val meal: String,
    val mealImage: String,
    var measures: List<String?>,
    val source: String?,
    val youtube: String
)
