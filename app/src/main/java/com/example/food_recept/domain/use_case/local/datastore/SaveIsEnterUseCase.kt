package com.example.food_recept.domain.use_case.local.datastore

import com.example.food_recept.datastore.preference_key.PreferenceKeys
import com.example.food_recept.domain.repository.local.DataStoreRepository
import javax.inject.Inject

class SaveIsEnterUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isEnter: Boolean) = dataStoreRepository.saveIsEnter(
        isEnterKey = PreferenceKeys.IS_ENTER,
        isEnter = isEnter
    )
}