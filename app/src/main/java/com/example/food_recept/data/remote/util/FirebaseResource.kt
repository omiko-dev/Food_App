package com.example.food_recept.data.remote.util

import com.example.food_recept.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FirebaseResource {
    fun <T : Any, R> handleResource(call: () -> R): Flow<Resource<T>> =
        flow {
            try {
                emit(Resource.Loader)
                val data = call()
                if (data != null) {
                    emit(Resource.Success(data as T))
                } else {
                    emit(Resource.Error("Try Again"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: ""))
            } finally {
                emit(Resource.Idle)
            }
        }
}