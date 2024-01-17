package com.example.food_recept.domain.use_case.local.datastore

import com.example.food_recept.datastore.preference_key.PreferenceKeys
import com.example.food_recept.domain.repository.local.DataStoreRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(token: String) = dataStoreRepository.saveToken(
        tokenKey = PreferenceKeys.TOKEN,
        token = token
    )
}