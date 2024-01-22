package com.example.food_recept.presentation.screen.search.event

sealed class SearchEvent{
    data class GetFoodByName(val name: String): SearchEvent()
}