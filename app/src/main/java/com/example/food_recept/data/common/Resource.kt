package com.example.food_recept.data.common

sealed class Resource<out T> {
    data class Success<out T>(val success: T): Resource<T>()
    data class Error(val error: String): Resource<Nothing>()
    data object Loader: Resource<Nothing>()
    data object Idle: Resource<Nothing>()
}