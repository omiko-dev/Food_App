package com.example.food_recept.data.service

import com.example.food_recept.data.dto.FoodResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodsByLetterService {
    @GET("search.php")
    suspend fun getFoodByLetter(@Query("s") param: String): Response<List<FoodResponseDto>>
}