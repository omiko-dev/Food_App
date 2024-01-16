package com.example.food_recept.presentation.mapper

import com.example.food_recept.domain.model.CountryModel
import com.example.food_recept.presentation.model.Country

fun CountryModel.toPresenter(): Country =
    Country(country = strArea)