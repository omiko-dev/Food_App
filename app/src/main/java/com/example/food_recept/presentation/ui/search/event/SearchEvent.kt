package com.example.food_recept.presentation.ui.search.event

sealed class SearchEvent{
    data class GetFoodByName(val name: String): SearchEvent()
}