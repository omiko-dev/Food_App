package com.example.food_recept.data.local.mapper

import com.example.food_recept.data.local.entity.UserEntity
import com.example.food_recept.domain.model.UserModel

fun UserEntity.toDomain(): UserModel {
    return UserModel(
        uid = userId,
        email = email ?: ""
    )
}