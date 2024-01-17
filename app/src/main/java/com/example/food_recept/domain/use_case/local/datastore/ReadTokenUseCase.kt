package com.example.food_recept.domain.use_case.local.datastore

import com.example.food_recept.datastore.preference_key.PreferenceKeys
import com.example.food_recept.domain.repository.local.DataStoreRepository
import javax.inject.Inject

class ReadTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke() = dataStoreRepository.readToken(PreferenceKeys.TOKEN)
}