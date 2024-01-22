package com.example.food_recept.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.food_recept.data.local.entity.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    suspend fun getAllFood(): List<FoodEntity>

    @Query("SELECT * FROM food WHERE foodId LIKE :foodId")
    suspend fun getFoodById(foodId: String): FoodEntity

    @Query("DELETE FROM food")
    fun deleteFood()

    @Delete
    fun deleteFoodById(foodEntity: FoodEntity)

    @Insert
    fun insertFood(foodEntity: FoodEntity)
}