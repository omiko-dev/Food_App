package com.example.food_recept.domain.use_case.remote.user

import com.example.food_recept.domain.repository.remote.UserFoodRepository
import javax.inject.Inject

class GetUserFoodUseCase @Inject constructor(
    private val userFoodRepository: UserFoodRepository
) {
    suspend operator fun  invoke(uid: String) = userFoodRepository.getUserFood(uid)
}