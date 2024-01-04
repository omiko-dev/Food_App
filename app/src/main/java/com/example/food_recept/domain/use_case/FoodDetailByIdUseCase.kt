package com.example.food_recept.domain.use_case

import com.example.food_recept.domain.repository.FoodDetailByIdRepository
import javax.inject.Inject

class FoodDetailByIdUseCase @Inject constructor(
    private val foodDetailByIdRepository: FoodDetailByIdRepository
) {
    suspend operator fun invoke(id: String) = foodDetailByIdRepository.getFoodDetailById(id = id)
}