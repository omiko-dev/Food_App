package com.example.food_recept.domain.use_case.remote.catogory

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.repository.remote.CategoryRepository
import com.example.food_recept.presentation.mapper.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoryRepository.getCategory().map {
        it.resourceMapper { foodList ->
            foodList.categories.map { food ->
                food.toPresenter()
            }
        }
    }
}