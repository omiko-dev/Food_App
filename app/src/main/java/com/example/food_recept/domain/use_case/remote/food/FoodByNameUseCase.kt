package com.example.food_recept.domain.use_case.remote.food

import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.presentation.mapper.toPresenter
import com.example.food_recept.domain.repository.remote.FoodRepository
import com.example.food_recept.presentation.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodByNameUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(
        name: String
    ): Flow<Resource<List<Food>>> {
        val foodName = name.trim().lowercase()
        return foodRepository.getFoodByName(foodName).map {
            it.resourceMapper { foodList ->
                foodList.meals.map { food ->
                    food.toPresenter()
                }
            }
        }
    }
}