package com.example.food_recept.domain.repository

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.response.FoodResponse
import kotlinx.coroutines.flow.Flow

interface FoodByLetterRepository {
    suspend fun getFoodByLetter(letter: String): Flow<Resource<List<FoodResponse>>>
}