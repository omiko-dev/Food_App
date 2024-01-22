package com.example.food_recept.data.local.util

import android.util.Log
import com.example.food_recept.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomDbResource {
    fun <T : Any> roomDbResource(call: suspend () -> T): Flow<Resource<T?>> =
        flow {
            try {
                emit(Resource.Loader)
                val data = call()
                emit(Resource.Success(success = data))
            } catch (e: Throwable) {
                Log.i("omiko", "${e.message}")
            }
        }
}