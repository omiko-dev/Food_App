package com.example.food_recept.presentation.screen.food_detail.event

import com.example.food_recept.presentation.model.Food

sealed class FoodDetailEvent{
    data class GetFoodById(val id: String): FoodDetailEvent()
    data class AddFavorite(val food: Food): FoodDetailEvent()
}
