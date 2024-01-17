package com.example.food_recept.domain.use_case.remote.auth

import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.presentation.mapper.toDomain
import com.example.food_recept.presentation.model.Register
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(register: Register) = authRepository.register(registerModelRequest = register.toDomain())
}