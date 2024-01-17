package com.example.food_recept.data.remote.common

fun <T, R> Resource<T>.resourceMapper(mapper: (T) -> R): Resource<R> {
    return when(this){
        is Resource.Success -> Resource.Success(success = mapper(success))
        is Resource.Error -> Resource.Error(error = error)
        is Resource.Loader -> Resource.Loader
        is Resource.Idle -> Resource.Idle
    }
}