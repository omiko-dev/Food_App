package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.request.LoginModelRequest
import com.example.food_recept.domain.request.RegisterModelRequest
import com.example.food_recept.domain.response.TokenModelResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginModelRequest: LoginModelRequest): Flow<Resource<TokenModelResponse>>
    suspend fun register(registerModelRequest: RegisterModelRequest): Flow<Resource<Unit>>
}