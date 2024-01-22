package com.example.food_recept.presentation.screen.login.event

import com.example.food_recept.presentation.model.Login
import com.example.food_recept.presentation.model.User

sealed class LoginEvent {
    data class LogIn(val login: Login): LoginEvent()
    data class InsertUserInDB(val user: User): LoginEvent()
}