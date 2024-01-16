package com.example.food_recept.data.model.country

import com.squareup.moshi.Json

data class CountryListDto (
    @Json(name = "meals")
    val meal: List<CountryDto>
)