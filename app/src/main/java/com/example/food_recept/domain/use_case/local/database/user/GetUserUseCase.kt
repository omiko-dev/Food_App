package com.example.food_recept.domain.use_case.local.database.user

import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.domain.repository.local.UserDatabaseRepository
import com.example.food_recept.presentation.mapper.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {
    operator fun invoke() = userDatabaseRepository.getUser()
        .map {
            it.resourceMapper { user ->
                user?.toPresenter()
            }
        }
}