package com.example.food_recept.domain.use_case.local.database.food

import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import javax.inject.Inject

class DeleteAllFoodDBUseCase @Inject constructor(
    private val foodDatabaseRepository: FoodDatabaseRepository
) {
    operator fun invoke() = foodDatabaseRepository.deleteFood()
}