package com.example.food_recept.domain.repository.remote

import com.example.food_recept.data.remote.common.Resource
import com.example.food_recept.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountry(): Flow<Resource<List<CountryModel>>>
}