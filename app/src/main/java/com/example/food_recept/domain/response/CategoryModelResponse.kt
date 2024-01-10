package com.example.food_recept.domain.response

import com.example.food_recept.domain.model.CategoryModel

data class CategoryModelResponse (
    val categories: List<CategoryModel>
)