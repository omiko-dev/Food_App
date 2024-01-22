package com.example.food_recept.data.local.repository

import com.example.food_recept.data.local.dao.UserDao
import com.example.food_recept.data.local.mapper.toData
import com.example.food_recept.data.local.mapper.toDomain
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.local.util.RoomDbResource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.model.UserModel
import com.example.food_recept.domain.repository.local.UserDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val roomDbResource: RoomDbResource
) : UserDatabaseRepository {
    override fun getUser(): Flow<Resource<UserModel?>> {
        return roomDbResource.roomDbResource {
            userDao.getUser()
        }.map {
            it.resourceMapper { user ->
                user?.toDomain()
            }
        }
    }

    override fun delete() {
        userDao.delete()
    }

    override fun insertUser(userModel: UserModel) {
        userDao.insertUser(userModel.toData())
    }
}