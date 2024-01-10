package com.example.food_recept.domain.response

import com.example.food_recept.domain.model.FoodModel

data class FoodModelResponse(
    val meals: List<FoodModel>
)

