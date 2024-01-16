package com.example.food_recept.presentation.screen.search.event

sealed class SearchEvent{
    data class GetFoodByName(val name: String, val country: String? = null, val category: String? = null): SearchEvent()
    data object GetCategoryList: SearchEvent()
    data object GetCountryList: SearchEvent()
}