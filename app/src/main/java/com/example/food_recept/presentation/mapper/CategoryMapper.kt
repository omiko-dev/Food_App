package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.model.CategoryModel
import com.example.food_recept.presentation.model.Category

fun CategoryModel.toPresenter(): Category{
    return Category(
        categoryId = idCategory,
        category = strCategory,
        categoryImage = strCategoryThumb,
        categoryDescription = strCategoryDescription
    )
}