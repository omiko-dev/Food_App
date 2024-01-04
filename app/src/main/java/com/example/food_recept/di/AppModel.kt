package com.example.food_recept.di

import com.example.food_recept.data.service.FoodDetailByIdService
import com.example.food_recept.data.service.FoodsByLetterService
import com.example.food_recept.data.service.SingleRandomFoodService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    private const val BASE_URL = "www.themealdb.com/api/json/v1/1/"

    @Provides
    @Singleton
    private fun provideRetrofitClient(): Retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )).build()



    @Provides
    @Singleton
    private fun provideFoodByLetterService(retrofit: Retrofit): FoodsByLetterService =
        retrofit.create(FoodsByLetterService::class.java)

    @Provides
    @Singleton
    private fun provideSingleRandomFoodService(retrofit: Retrofit): SingleRandomFoodService =
        retrofit.create(SingleRandomFoodService::class.java)

    @Provides
    @Singleton
    private fun provideFoodDetailByIdService(retrofit: Retrofit): FoodDetailByIdService =
        retrofit.create(FoodDetailByIdService::class.java)

}