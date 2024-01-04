package com.example.food_recept.domain.repository

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.response.FoodResponse
import kotlinx.coroutines.flow.Flow

interface FoodDetailByIdRepository {
    suspend fun getFoodDetailById(id: String): Flow<Resource<FoodResponse>>
}