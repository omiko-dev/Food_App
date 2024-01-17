package com.example.food_recept.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.food_recept.data.local.repository.DataStoreRepositoryImpl
import com.example.food_recept.data.remote.common.HandleResource
import com.example.food_recept.data.remote.repository.AuthRepositoryImpl
import com.example.food_recept.data.remote.repository.CategoryRepositoryImpl
import com.example.food_recept.data.remote.repository.CountryRepositoryImpl
import com.example.food_recept.data.remote.repository.FoodRepositoryImpl
import com.example.food_recept.data.remote.service.AuthService
import com.example.food_recept.data.remote.service.CategoryService
import com.example.food_recept.data.remote.service.CountryService
import com.example.food_recept.data.remote.service.FoodService
import com.example.food_recept.domain.repository.local.DataStoreRepository
import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.domain.repository.remote.CategoryRepository
import com.example.food_recept.domain.repository.remote.CountryRepository
import com.example.food_recept.domain.repository.remote.FoodRepository
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
    fun provideHandleResource(): HandleResource = HandleResource()

    @Provides
    @Singleton
    fun provideFoodRepository(foodService: FoodService, handleResource: HandleResource): FoodRepository =
        FoodRepositoryImpl(foodService = foodService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryService: CategoryService, handleResource: HandleResource): CategoryRepository =
        CategoryRepositoryImpl(categoryService = categoryService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideCountryRepository(countryService: CountryService, handleResource: HandleResource): CountryRepository =
        CountryRepositoryImpl(countryService = countryService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService, handleResource: HandleResource): AuthRepository =
        AuthRepositoryImpl(authService = authService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository =
        DataStoreRepositoryImpl(dataStore = dataStore)
}