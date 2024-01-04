package com.example.food_recept.data.repository_impl

import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.mapper.resourceMapper
import com.example.food_recept.data.mapper.toDomain
import com.example.food_recept.data.service.FoodsByLetterService
import com.example.food_recept.domain.repository.FoodByLetterRepository
import com.example.food_recept.domain.response.FoodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodByLetterRepositoryImpl @Inject constructor(
    private val foodsByLetterService: FoodsByLetterService,
    private val handleResource: HandleResource = HandleResource()
) :
    FoodByLetterRepository {
    override suspend fun getFoodByLetter(letter: String): Flow<Resource<List<FoodResponse>>> {
        return handleResource.handleResource {
            foodsByLetterService.getFoodByLetter(letter)
        }.map {
            it.resourceMapper { foodList ->
                foodList.map { food ->
                    food.toDomain()
                }
            }
        }
    }
}