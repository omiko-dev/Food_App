package com.example.food_recept.presentation.model

data class Category (
    val categoryId: String,
    val category: String,
    val categoryImage: String,
    val categoryDescription: String,
    var isChecked: Boolean = false
)