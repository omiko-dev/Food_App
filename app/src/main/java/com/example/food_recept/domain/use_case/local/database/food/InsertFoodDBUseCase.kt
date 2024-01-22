package com.example.food_recept.domain.use_case.local.database.food

import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import com.example.food_recept.presentation.mapper.food.toDomain
import com.example.food_recept.presentation.model.Food
import javax.inject.Inject

class InsertFoodDBUseCase @Inject constructor(
    private val foodDatabaseRepository: FoodDatabaseRepository
) {
    operator fun invoke(food: Food) =
        foodDatabaseRepository.insertFood(foodModel = food.toDomain())
}