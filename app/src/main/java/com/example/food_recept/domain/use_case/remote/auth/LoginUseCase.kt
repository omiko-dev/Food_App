package com.example.food_recept.domain.use_case.remote.auth

import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.presentation.mapper.toDomain
import com.example.food_recept.presentation.mapper.toPresenter
import com.example.food_recept.presentation.model.Login
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(login: Login) =
        authRepository.login(login.toDomain())
            .map {
                it.resourceMapper { token ->
                    token.toPresenter()
                }
            }
}