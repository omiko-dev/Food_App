package com.example.food_recept.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.food_recept.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE userId LIKE :id")
    fun getUser(id: Int): Flow<User>
    @Delete
    fun delete(user: User)
    @Insert
    fun insertUser(user: User)
}