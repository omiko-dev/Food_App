package com.example.food_recept.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Food")
data class FoodEntity(
    @PrimaryKey
    val foodId: Int,
    val area: String,
    val category: String,
    @ColumnInfo(name = "image_source")
    val imageSource: String,
    val instructions: String,
    val meal: String,
    @ColumnInfo(name = "meal_thumb")
    val mealThumb: String,
    val source: String,
    val tags: String,
    val youtube: String
)
