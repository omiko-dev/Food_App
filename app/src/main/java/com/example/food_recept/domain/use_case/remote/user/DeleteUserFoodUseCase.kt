package com.example.food_recept.domain.use_case.remote.user

import com.example.food_recept.domain.repository.remote.UserFoodRepository
import javax.inject.Inject

class DeleteUserFoodUseCase @Inject constructor(
    private val userFoodRepository: UserFoodRepository
) {
    suspend operator fun invoke(foodId: String) = userFoodRepository.deleteUserFood(foodId = foodId)
}