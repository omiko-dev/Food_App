package com.example.food_recept.domain.use_case.local.database.user

import com.example.food_recept.domain.repository.local.UserDatabaseRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    operator fun invoke() = userDatabaseRepository.delete()
}