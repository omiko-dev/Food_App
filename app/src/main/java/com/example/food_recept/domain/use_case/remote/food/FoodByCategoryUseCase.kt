package com.example.food_recept.domain.use_case.remote.food

import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.presentation.mapper.toPresenter
import com.example.food_recept.domain.repository.remote.FoodRepository
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