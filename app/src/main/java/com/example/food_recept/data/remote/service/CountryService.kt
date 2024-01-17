package com.example.food_recept.data.remote.service

import com.example.food_recept.data.remote.model.country.CountryListDto
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET("list.php?a=list")
    suspend fun getAllCountry(): Response<CountryListDto>
}