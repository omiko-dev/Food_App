package com.example.food_recept.data.remote.mapper.country

import com.example.food_recept.data.remote.model.country.CountryDto
import com.example.food_recept.domain.model.CountryModel

fun CountryDto.toDomain(): CountryModel {
    return CountryModel(strArea)
}