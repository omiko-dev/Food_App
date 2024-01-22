package com.example.food_recept.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.food_recept.data.local.dao.FoodDao
import com.example.food_recept.data.local.dao.UserDao
import com.example.food_recept.data.local.entity.FoodEntity
import com.example.food_recept.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, FoodEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao
}
