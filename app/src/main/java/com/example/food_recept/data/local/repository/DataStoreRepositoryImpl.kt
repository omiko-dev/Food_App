package com.example.food_recept.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.food_recept.domain.repository.local.DataStoreRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun saveIsEnter(isEnterKey: Preferences.Key<Boolean>, isEnter: Boolean) {
        dataStore.edit {
            it[isEnterKey] = isEnter
        }
    }

    override suspend fun saveToken(tokenKey: Preferences.Key<String>, token: String) {
        dataStore.edit {
            it[tokenKey] = token
        }
    }

    override fun readIsEnter(isEnterKey: Preferences.Key<Boolean>) =
        dataStore.data.map {
            it[isEnterKey] ?: false
        }

    override fun readToken(tokenKey: Preferences.Key<String>) =
        dataStore.data.map {
            it[tokenKey] ?: ""
        }

    override suspend fun clearToken(tokenKey: Preferences.Key<String>) {
        dataStore.edit {
            it.remove(tokenKey)
        }
    }
}
