package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.model.FoodByCategoryModel
import com.example.food_recept.domain.response.FoodModelResponse
import com.example.food_recept.presentation.model.Category
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getFoodByName(name: String): Flow<Resource<FoodModelResponse>>
    suspend fun getFoodDetailById(id: String): Flow<Resource<FoodModelResponse>>
    suspend fun getSingleRandomFood(): Flow<Resource<FoodModelResponse>>
    suspend fun getFoodByCategory(category: String): Flow<Resource<List<FoodByCategoryModel>>>
}