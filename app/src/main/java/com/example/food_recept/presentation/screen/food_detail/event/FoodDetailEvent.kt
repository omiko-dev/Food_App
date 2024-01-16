package com.example.food_recept.presentation.screen.food_detail.event

sealed class FoodDetailEvent(){
    data class GetFoodById(val id: String): FoodDetailEvent()
}
