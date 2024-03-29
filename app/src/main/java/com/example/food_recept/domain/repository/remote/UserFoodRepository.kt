package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.model.FoodModel
import kotlinx.coroutines.flow.Flow

interface UserFoodRepository {
    suspend fun getUserFood(uid: String): Flow<Resource<Any>>
    suspend fun addUserFood(foodModel: FoodModel)
    suspend fun deleteUserFood(foodId: String)
}