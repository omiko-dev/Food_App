package com.example.food_recept.data.local.repository

import com.example.food_recept.data.local.dao.FoodDao
import com.example.food_recept.data.local.mapper.toData
import com.example.food_recept.data.local.mapper.toDomain
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.local.util.RoomDbResource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.model.FoodModel
import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodDatabaseRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao, private val roomDbResource: RoomDbResource
) : FoodDatabaseRepository {
    override suspend fun getAllFood(): Flow<Resource<List<FoodModel>>> =
        roomDbResource.roomDbResource {
            foodDao.getAllFood()
        }.map {
            it.resourceMapper {
                it!!.map {
                    it.toDomain()
                }
            }
        }

    override suspend fun getFoodById(foodId: String): Flow<Resource<FoodModel?>> =
        roomDbResource.roomDbResource {
            foodDao.getFoodById(foodId = foodId)
        }.map {
            it.resourceMapper { food ->
                food?.toDomain()
            }
        }

    override fun deleteFoodById(foodModel: FoodModel) =
        foodDao.deleteFoodById(foodEntity = foodModel.toData())

    override fun deleteFood() {
        foodDao.deleteFood()
    }

    override fun insertFood(foodModel: FoodModel) =
        foodDao.insertFood(foodEntity = foodModel.toData())
}