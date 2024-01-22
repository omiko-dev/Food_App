package com.example.food_recept.domain.use_case.remote.auth

import com.example.food_recept.domain.repository.remote.AuthRepository
import javax.inject.Inject

class LogoutUserCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.logout()
}