package com.example.food_recept.domain.use_case

import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.presentation.mapper.toPresenter
import com.example.food_recept.domain.repository.FoodRepository
import com.example.food_recept.presentation.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodDetailByIdUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(id: String): Flow<Resource<Food>> {
       return  foodRepository.getFoodDetailById(id = id).map {
            it.resourceMapper { foodList ->
                foodList.meals.map { food ->
                    food.toPresenter()
                }[0]
            }
        }
    }
}