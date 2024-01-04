package com.example.food_recept.data.repository_impl

import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.mapper.resourceMapper
import com.example.food_recept.data.mapper.toDomain
import com.example.food_recept.data.service.FoodDetailByIdService
import com.example.food_recept.domain.repository.FoodDetailByIdRepository
import com.example.food_recept.domain.response.FoodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodDetailByIdRepositoryImpl @Inject constructor(
    private val foodDetailByIdService: FoodDetailByIdService,
    private val handleResource: HandleResource = HandleResource()
) : FoodDetailByIdRepository {
    override suspend fun getFoodDetailById(id: String): Flow<Resource<FoodResponse>> {
        return handleResource.handleResource {
            foodDetailByIdService.getFoodDetailByIdService(id)
        }.map {
            it.resourceMapper { food ->
                food.toDomain()
            }
        }
    }
}