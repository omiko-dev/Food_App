package com.example.food_recept.domain.use_case.local.database.food

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import com.example.food_recept.presentation.mapper.food.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFoodByIdDBUseCase @Inject constructor(
    private val foodDatabaseRepository: FoodDatabaseRepository
) {
    suspend operator fun invoke(foodId: String) =
        foodDatabaseRepository.getFoodById(foodId = foodId)
            .map {
                it.resourceMapper { food ->
                    food?.toPresenter()
                }
            }
}