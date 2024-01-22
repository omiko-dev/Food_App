package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.request.LoginModelRequest
import com.example.food_recept.domain.request.RegisterModelRequest
import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(loginModelRequest: LoginModelRequest): Flow<Resource<FirebaseUser>>
    suspend fun register(registerModelRequest: RegisterModelRequest): Flow<Resource<FirebaseUser>>
    fun logout()
}