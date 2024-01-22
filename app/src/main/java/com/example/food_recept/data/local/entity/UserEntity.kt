package com.example.food_recept.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey
    val userId: String,
    @ColumnInfo(name = "email")
    val email: String?
)
