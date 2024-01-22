package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.model.UserModel
import com.example.food_recept.presentation.model.User

fun UserModel.toPresenter() =
    User(
        uid = uid,
        email = email
    )