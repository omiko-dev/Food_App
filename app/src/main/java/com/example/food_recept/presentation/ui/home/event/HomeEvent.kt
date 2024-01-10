package com.example.food_recept.presentation.ui.home.event

sealed class HomeEvent{
    data object GetCategory: HomeEvent()
    data class GetFoodByCategory(val category: String): HomeEvent()
    data object GetSingleRandomFood: HomeEvent()
}