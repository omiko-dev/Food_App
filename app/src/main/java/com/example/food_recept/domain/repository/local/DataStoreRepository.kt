package com.example.food_recept.domain.repository.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveIsEnter(
        isEnterKey: Preferences.Key<Boolean>,
        isEnter: Boolean
    )

    suspend fun saveToken(
        tokenKey: Preferences.Key<String>,
        token: String,
    )

    fun readIsEnter(
        isEnterKey: Preferences.Key<Boolean>
    ): Flow<Boolean>

    fun readToken(
        tokenKey: Preferences.Key<String>
    ): Flow<String>

    suspend fun clearToken(
        tokenKey: Preferences.Key<String>
    )
}