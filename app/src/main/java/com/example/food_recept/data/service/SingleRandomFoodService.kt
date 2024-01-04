package com.example.food_recept.data.service

import com.example.food_recept.data.dto.FoodResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface SingleRandomFoodService {
    @GET("random.php")
    suspend fun getSingleRandomFoodService(): Response<FoodResponseDto>
}