package com.example.food_recept.data.remote.common

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class HandleResource {
    fun <T: Any> handleResource(call: suspend () -> Response<T>): Flow<Resource<T>> =
        flow {
            try {
                emit(Resource.Loader)
                val data = call()
                if(data.isSuccessful){
                    emit(Resource.Success(success = data.body()!!))
                }else{
                    emit(Resource.Error(error = data.errorBody()?.string() ?: ""))
                }
            }catch (e: Throwable){
                Log.i("omiko", "${e.message}")
            }finally {
                emit(Resource.Idle)
            }
        }
}