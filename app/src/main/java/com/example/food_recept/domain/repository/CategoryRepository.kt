package com.example.food_recept.domain.repository

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.response.CategoryModelResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategory(): Flow<Resource<CategoryModelResponse>>
}