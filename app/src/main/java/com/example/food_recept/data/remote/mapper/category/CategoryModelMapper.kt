package com.example.food_recept.data.remote.mapper.category

import com.example.food_recept.data.remote.model.category.CategoryDto
import com.example.food_recept.domain.model.CategoryModel

fun CategoryDto.toDomain(): CategoryModel{
    return CategoryModel(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryDescription = strCategoryDescription,
        strCategoryThumb = strCategoryThumb
    )
}