package com.example.food_recept.domain.use_case

import com.example.food_recept.domain.repository.SingleRandomFoodRepository
import javax.inject.Inject

class SingleRandomFoodUseCase @Inject constructor(
    private val singleRandomFoodRepository: SingleRandomFoodRepository
) {
    suspend operator fun invoke() = singleRandomFoodRepository.getSingleRandomFood()
}