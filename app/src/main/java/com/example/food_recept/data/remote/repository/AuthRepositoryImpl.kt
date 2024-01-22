package com.example.food_recept.data.remote.repository

import android.util.Log
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.remote.util.await
import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.domain.request.LoginModelRequest
import com.example.food_recept.domain.request.RegisterModelRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun login(loginModelRequest: LoginModelRequest): Flow<Resource<FirebaseUser>> =
        flow {
            try {
                emit(Resource.Loader)
                val result = firebaseAuth.signInWithEmailAndPassword(
                    loginModelRequest.email,
                    loginModelRequest.password
                ).await()
                val user = result.user
                if (user != null) {
                    emit(Resource.Success(user))
                } else {
                    emit(Resource.Error("Authentication failed. Please check your credentials and try again."))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Incorrect Email Or Password"))
                Log.i("omiko", "catch")

            }
        }

    override suspend fun register(registerModelRequest: RegisterModelRequest): Flow<Resource<FirebaseUser>> =
        flow {
            try {
                emit(Resource.Loader)
                val result = firebaseAuth.createUserWithEmailAndPassword(
                    registerModelRequest.email,
                    registerModelRequest.password
                ).await()
                val user = result.user
                if (user != null) {
                    emit(Resource.Success(user))
                } else {
                    emit(Resource.Error("Authentication failed. Please check your credentials and try again."))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Enter Another Email"))
            }
        }

    override fun logout() {
        firebaseAuth.signOut()
    }
}