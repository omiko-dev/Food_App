package com.example.food_recept.presentation.screen.register.event

import com.example.food_recept.presentation.model.Register

sealed class RegisterEvent {
    data class SignUp(val register: Register): RegisterEvent()
}