package com.example.food_recept.domain.repository.local

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDatabaseRepository {
    fun getUser(): Flow<Resource<UserModel?>>
    fun delete()
    fun insertUser(userModel: UserModel)
}