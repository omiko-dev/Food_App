package com.example.food_recept.data.remote.service

import com.example.food_recept.data.remote.model.category.CategoryListDto
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("categories.php")
    suspend fun getFoodCategories(): Response<CategoryListDto>
}