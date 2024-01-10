package com.example.food_recept.data.repository

import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.data.mapper.category.toDomain
import com.example.food_recept.data.service.CategoryService
import com.example.food_recept.domain.repository.CategoryRepository
import com.example.food_recept.domain.response.CategoryModelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryService: CategoryService,
    private val handleResource: HandleResource = HandleResource()
) : CategoryRepository {
    override suspend fun getCategory(): Flow<Resource<CategoryModelResponse>> {
        return handleResource.handleResource {
            categoryService.getFoodCategories()
        }.map {
            it.resourceMapper { foodList ->
                foodList.toDomain()
            }
        }
    }
}