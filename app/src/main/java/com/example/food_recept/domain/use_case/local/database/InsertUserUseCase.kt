package com.example.food_recept.domain.use_case.local.database

import com.example.food_recept.data.local.repository.UserDatabaseRepositoryImpl
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepositoryImpl
) {
    suspend operator fun invoke() = userDatabaseRepository.insertUser()
}