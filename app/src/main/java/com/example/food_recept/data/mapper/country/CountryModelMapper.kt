package com.example.food_recept.data.mapper.country

import com.example.food_recept.data.model.country.CountryDto
import com.example.food_recept.domain.model.CountryModel

fun CountryDto.toDomain(): CountryModel {
    return CountryModel(strArea)
}