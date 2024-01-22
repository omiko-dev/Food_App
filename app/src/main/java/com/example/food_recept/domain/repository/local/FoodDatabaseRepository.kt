package com.example.food_recept.domain.repository.local

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.model.FoodModel
import kotlinx.coroutines.flow.Flow

interface FoodDatabaseRepository {
    suspend fun getAllFood(): Flow<Resource<List<FoodModel>>>

    suspend fun getFoodById(foodId: String): Flow<Resource<FoodModel?>>

    fun deleteFoodById(foodModel: FoodModel)

    fun deleteFood()

    fun insertFood(foodModel: FoodModel)
}