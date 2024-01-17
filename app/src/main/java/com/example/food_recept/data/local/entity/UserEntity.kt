package com.example.food_recept.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?

)
