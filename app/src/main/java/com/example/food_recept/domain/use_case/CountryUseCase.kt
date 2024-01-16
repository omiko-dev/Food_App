package com.example.food_recept.domain.use_case

import android.util.Log
import com.example.food_recept.data.common.resourceMapper
import com.example.food_recept.presentation.mapper.toPresenter
import com.example.food_recept.domain.repository.CountryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke() = countryRepository.getAllCountry()
        .map {
            it.resourceMapper { countryList ->
                Log.i("omiko", countryList.toString())
                countryList.map { country ->
                    country.toPresenter()
                }
            }
        }
}