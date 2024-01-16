package com.example.food_recept.data.repository

import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.common.Resource
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.data.mapper.country.toDomain
import com.example.food_recept.data.service.CountryService
import com.example.food_recept.domain.model.CountryModel
import com.example.food_recept.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryService: CountryService,
    private val handleResource: HandleResource = HandleResource()
) : CountryRepository {
    override suspend fun getAllCountry(): Flow<Resource<List<CountryModel>>> {
        return handleResource.handleResource {
            countryService.getAllCountry()
        }.map {
            it.resourceMapper { countryList ->
                countryList.meal.map { country ->
                    country.toDomain()
                }
            }
        }
    }
}