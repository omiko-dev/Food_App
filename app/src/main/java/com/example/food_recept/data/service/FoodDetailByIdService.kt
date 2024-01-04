package com.example.food_recept.data.service

import com.example.food_recept.data.dto.FoodResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDetailByIdService {
    @GET("lookup.php")
    suspend fun getFoodDetailByIdService(@Query("i") id: String): Response<FoodResponseDto>
}