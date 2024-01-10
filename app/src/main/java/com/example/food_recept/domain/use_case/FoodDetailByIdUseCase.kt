package com.example.food_recept.domain.use_case

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.repository.FoodRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodDetailByIdUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(id: String) =
        foodRepository.getFoodDetailById(id = id).map {
            it.resourceMapper { food ->
                food.meals
            }
        }
}