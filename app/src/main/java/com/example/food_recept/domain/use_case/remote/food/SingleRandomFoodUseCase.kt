package com.example.food_recept.domain.use_case.remote.food

import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.presentation.mapper.food.toPresenter
import com.example.food_recept.domain.repository.remote.FoodRepository
import com.example.food_recept.presentation.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SingleRandomFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(): Flow<Resource<Food>> =
        foodRepository.getSingleRandomFood().map {
            it.resourceMapper { food ->
                food.meals[0].toPresenter()
            }
        }
}