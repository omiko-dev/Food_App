package com.example.food_recept.di

import com.example.food_recept.data.repository.CategoryRepositoryImpl
import com.example.food_recept.data.repository.CountryRepositoryImpl
import com.example.food_recept.data.repository.FoodRepositoryImpl
import com.example.food_recept.data.service.CategoryService
import com.example.food_recept.data.service.CountryService
import com.example.food_recept.data.service.FoodService
import com.example.food_recept.domain.repository.CategoryRepository
import com.example.food_recept.domain.repository.CountryRepository
import com.example.food_recept.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFoodRepository(foodService: FoodService): FoodRepository =
        FoodRepositoryImpl(foodService)

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryService: CategoryService): CategoryRepository =
        CategoryRepositoryImpl(categoryService = categoryService)

    @Provides
    @Singleton
    fun provideCountryRepository(countryService: CountryService): CountryRepository =
        CountryRepositoryImpl(countryService = countryService)

}