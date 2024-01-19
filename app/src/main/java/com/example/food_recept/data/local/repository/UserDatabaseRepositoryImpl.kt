package com.example.food_recept.data.local.repository

import com.example.food_recept.data.local.dao.UserDao
import com.example.food_recept.data.local.entity.User
import javax.inject.Inject

class UserDatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) {

    fun insertUser(){
        userDao.insertUser(User(userId = 0, name = "omiko"))
    }

}