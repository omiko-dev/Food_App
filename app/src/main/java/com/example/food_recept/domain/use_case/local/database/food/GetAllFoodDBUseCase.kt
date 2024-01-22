package com.example.food_recept.domain.use_case.local.database.food

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import com.example.food_recept.presentation.mapper.food.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllFoodDBUseCase @Inject constructor(
    private val foodDatabaseRepository: FoodDatabaseRepository
) {
    suspend operator fun invoke() = foodDatabaseRepository.getAllFood().map {
        it.resourceMapper { foodList ->
            foodList.map { food ->
                food.toPresenter()
            }
        }
    }
}