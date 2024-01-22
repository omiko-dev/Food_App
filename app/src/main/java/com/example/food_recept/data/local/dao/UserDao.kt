package com.example.food_recept.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.food_recept.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): UserEntity
    @Query("DELETE FROM user")
    fun delete()
    @Insert
    fun insertUser(userEntity: UserEntity)
}