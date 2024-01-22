package com.example.food_recept.domain.use_case.local.database.user

import com.example.food_recept.domain.repository.local.UserDatabaseRepository
import com.example.food_recept.presentation.mapper.toDomain
import com.example.food_recept.presentation.model.User
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    operator fun invoke(user: User) = userDatabaseRepository.insertUser(userModel = user.toDomain())
}