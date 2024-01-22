package com.example.food_recept.presentation.screen.home.event

sealed class HomeEvent{
    data object GetCategory: HomeEvent()
    data class GetFoodByCategory(val category: String): HomeEvent()
    data object GetSingleRandomFood: HomeEvent()
    data object LogOut: HomeEvent()
}