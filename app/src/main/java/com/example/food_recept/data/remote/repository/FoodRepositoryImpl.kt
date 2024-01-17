package com.example.food_recept.data.remote.repository

import com.example.food_recept.data.remote.common.HandleResource
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.data.remote.mapper.food.toDomain
import com.example.food_recept.data.remote.service.FoodService
import com.example.food_recept.domain.model.FoodByCategoryModel
import com.example.food_recept.domain.repository.remote.FoodRepository
import com.example.food_recept.domain.response.FoodModelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodService: FoodService,
    private val handleResource: HandleResource
) : FoodRepository {
    override suspend fun getFoodByName(name: String): Flow<Resource<FoodModelResponse>> {
        return handleResource.handleResource {
            foodService.getFoodByNameService(param = name)
        }.map { foodList ->
            foodList.resourceMapper { food ->
                food.toDomain()
            }
        }
    }

    override suspend fun getFoodDetailById(id: String): Flow<Resource<FoodModelResponse>> {
        return handleResource.handleResource {
            foodService.getFoodDetailByIdService(id = id)
        }.map { foodList ->
            foodList.resourceMapper { food ->
                food.toDomain()
            }
        }
    }

    override suspend fun getSingleRandomFood(): Flow<Resource<FoodModelResponse>> {
        return handleResource.handleResource {
            foodService.getSingleRandomFoodService()
        }.map { foodList ->
            foodList.resourceMapper { food ->
                food.toDomain()
            }
        }
    }

    override suspend fun getFoodByCategory(category: String): Flow<Resource<List<FoodByCategoryModel>>> {
        return handleResource.handleResource {
            foodService.getFoodByCategoryService(category = category)
        }.map {
            it.resourceMapper { foodList ->
                foodList.meals.map { food ->
                    food.toDomain()
                }
            }
        }
    }
}