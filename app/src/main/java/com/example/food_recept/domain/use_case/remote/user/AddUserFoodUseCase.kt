package com.example.food_recept.domain.use_case.remote.user

import com.example.food_recept.domain.repository.remote.UserFoodRepository
import com.example.food_recept.presentation.mapper.food.toDomain
import com.example.food_recept.presentation.model.Food
import javax.inject.Inject

class AddUserFoodUseCase @Inject constructor(
    private val userFoodRepository: UserFoodRepository
) {
    suspend operator fun invoke(food: Food) = userFoodRepository.addUserFood(foodModel = food.toDomain())
}