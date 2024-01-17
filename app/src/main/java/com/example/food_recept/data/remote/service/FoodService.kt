package com.example.food_recept.data.remote.service

import com.example.food_recept.data.remote.model.food_by_category.FoodByCategoryListDto
import com.example.food_recept.data.remote.model.food.FoodModelResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService{
    @GET("lookup.php")
    suspend fun getFoodDetailByIdService(@Query("i") id: String): Response<FoodModelResponseDto>

    @GET("search.php")
    suspend fun getFoodByNameService(@Query("s") param: String): Response<FoodModelResponseDto>

    @GET("random.php")
    suspend fun getSingleRandomFoodService(): Response<FoodModelResponseDto>

    @GET("filter.php")
    suspend fun getFoodByCategoryService(@Query("c") category: String): Response<FoodByCategoryListDto>
}