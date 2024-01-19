package com.example.food_recept.di

import android.content.Context
import androidx.room.Room
import com.example.food_recept.BuildConfig
import com.example.food_recept.data.local.database.AppDatabase
import com.example.food_recept.data.remote.common.HandleResource
import com.example.food_recept.data.remote.service.AuthService
import com.example.food_recept.data.remote.service.CategoryService
import com.example.food_recept.data.remote.service.CountryService
import com.example.food_recept.data.remote.service.FoodService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "data_base"
        ).addMigrations(AppDatabase.migration3To4).build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    @FoodRetrofitClient
    fun provideFoodRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
        .baseUrl(BuildConfig.FOOD_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )).build()

    @Provides
    @Singleton
    @UserRetrofitClient
    fun provideUserRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.USER_BASE_URL)
            .client(okHttpClient)
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