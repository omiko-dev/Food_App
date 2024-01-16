package com.example.food_recept.di

import com.example.food_recept.BuildConfig
import com.example.food_recept.data.service.AuthService
import com.example.food_recept.data.service.CategoryService
import com.example.food_recept.data.service.CountryService
import com.example.food_recept.data.service.FoodService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoodRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserRetrofitClient

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Provides
    @Singleton
    @FoodRetrofitClient
    fun provideFoodRetrofitClient(): Retrofit =
        Retrofit.Builder()
        .baseUrl(BuildConfig.FOOD_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )).build()

    @Provides
    @Singleton
    @UserRetrofitClient
    fun provideUserRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.USER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )).build()

    @Provides
    @Singleton
    fun provideFoodService(@FoodRetrofitClient retrofit: Retrofit): FoodService =
        retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun provideCategoryService(@FoodRetrofitClient retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Provides
    @Singleton
    fun provideCountryService(@FoodRetrofitClient retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)


    @Provides
    @Singleton
    fun provideAuthService(@UserRetrofitClient retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}