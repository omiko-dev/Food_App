package com.example.food_recept.presentation.screen.saved.event

sealed class SavedEvent {
    data object GetAllFood: SavedEvent()
}