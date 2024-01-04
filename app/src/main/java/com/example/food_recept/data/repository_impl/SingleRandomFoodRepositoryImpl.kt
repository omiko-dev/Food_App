package com.example.food_recept.data.repository_impl

import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.mapper.resourceMapper
import com.example.food_recept.data.mapper.toDomain
import com.example.food_recept.data.service.SingleRandomFoodService
import com.example.food_recept.domain.repository.SingleRandomFoodRepository
import com.example.food_recept.domain.response.FoodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SingleRandomFoodRepositoryImpl @Inject constructor(
    private val singleRandomFoodService: SingleRandomFoodService,
    private val handleResource: HandleResource = HandleResource()
): SingleRandomFoodRepository {
    override suspend fun getSingleRandomFood(): Flow<Resource<FoodResponse>> {
        return handleResource.handleResource {
            singleRandomFoodService.getSingleRandomFoodService()
        }.map {
            it.resourceMapper { food ->
                food.toDomain()
            }
        }
    }
}