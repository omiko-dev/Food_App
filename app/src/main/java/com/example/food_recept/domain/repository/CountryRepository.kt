package com.example.food_recept.domain.repository

import com.example.food_recept.data.common.Resource
import com.example.food_recept.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountry(): Flow<Resource<List<CountryModel>>>
}