package com.example.food_recept.data.remote.repository

import com.example.food_recept.data.remote.common.HandleResource
import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.data.remote.common.resourceMapper
import com.example.food_recept.data.remote.mapper.login.toData
import com.example.food_recept.data.remote.mapper.register.toData
import com.example.food_recept.data.remote.mapper.token.toDomain
import com.example.food_recept.data.remote.service.AuthService
import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.domain.request.LoginModelRequest
import com.example.food_recept.domain.request.RegisterModelRequest
import com.example.food_recept.domain.response.TokenModelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val handleResource: HandleResource
): AuthRepository {
    override suspend fun login(loginModelRequest: LoginModelRequest): Flow<Resource<TokenModelResponse>> {
        return handleResource.handleResource {
            authService.logInService(loginRequestDto = loginModelRequest.toData() )
        }.map {
            it.resourceMapper { token ->
                token.toDomain()
            }
        }
    }

    override suspend fun register(registerModelRequest: RegisterModelRequest): Flow<Resource<Unit>> {
        return handleResource.handleResource {
            authService.registerService(registerRequestDto = registerModelRequest.toData())
        }
    }
}