package com.example.food_recept.domain.use_case

import com.example.food_recept.domain.repository.FoodByLetterRepository
import javax.inject.Inject

class FoodByLetterRepository @Inject constructor(
    private val foodByLetterRepository: FoodByLetterRepository
) {
    suspend operator fun invoke(letter: String) = foodByLetterRepository.getFoodByLetter(letter)
}