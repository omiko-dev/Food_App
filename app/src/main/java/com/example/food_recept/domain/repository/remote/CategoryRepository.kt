package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.response.CategoryModelResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategory(): Flow<Resource<CategoryModelResponse>>
}