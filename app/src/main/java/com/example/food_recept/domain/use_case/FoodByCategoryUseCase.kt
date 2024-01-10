package com.example.food_recept.domain.use_case

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.mapper.toPresenter
import com.example.food_recept.domain.repository.FoodRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodByCategoryUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(category: String) =
        foodRepository.getFoodByCategory(category = category)
            .map {
                it.resourceMapper { foodList ->
                    foodList.map { food ->
                        food.toPresenter()
                    }
                }
            }
}