package com.example.food_recept.data.local.mapper

import com.example.food_recept.data.local.entity.UserEntity
import com.example.food_recept.domain.model.UserModel

fun UserModel.toData() =
    UserEntity(
        userId = uid,
        email = email
    )