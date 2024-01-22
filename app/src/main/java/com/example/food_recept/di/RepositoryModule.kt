package com.example.food_recept.di

import com.example.food_recept.data.local.dao.FoodDao
import com.example.food_recept.data.local.dao.UserDao
import com.example.food_recept.data.local.repository.FoodDatabaseRepositoryImpl
import com.example.food_recept.data.local.repository.UserDatabaseRepositoryImpl
import com.example.food_recept.data.remote.util.FirebaseResource
import com.example.food_recept.data.common.HandleResource
import com.example.food_recept.data.local.util.RoomDbResource
import com.example.food_recept.data.remote.repository.AuthRepositoryImpl
import com.example.food_recept.data.remote.repository.CategoryRepositoryImpl
import com.example.food_recept.data.remote.repository.FoodRepositoryImpl
import com.example.food_recept.data.remote.repository.UserFoodRepositoryImpl
import com.example.food_recept.data.remote.service.CategoryService
import com.example.food_recept.data.remote.service.FoodService
import com.example.food_recept.domain.repository.local.FoodDatabaseRepository
import com.example.food_recept.domain.repository.local.UserDatabaseRepository
import com.example.food_recept.domain.repository.remote.AuthRepository
import com.example.food_recept.domain.repository.remote.CategoryRepository
import com.example.food_recept.domain.repository.remote.FoodRepository
import com.example.food_recept.domain.repository.remote.UserFoodRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseResource(): FirebaseResource = FirebaseResource()

    @Provides
    @Singleton
    fun provideRoomDbResource(): RoomDbResource = RoomDbResource()

    @Provides
    @Singleton
    fun provideFoodRepository(
        foodService: FoodService, handleResource: HandleResource
    ): FoodRepository =
        FoodRepositoryImpl(foodService = foodService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryService: CategoryService, handleResource: HandleResource
    ): CategoryRepository =
        CategoryRepositoryImpl(categoryService = categoryService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository =
        AuthRepositoryImpl(firebaseAuth = firebaseAuth)


    @Provides
    @Singleton
    fun provideUserRepository(
        fireStore: FirebaseFirestore, firebaseResource: FirebaseResource, firebaseAuth: FirebaseAuth
    ): UserFoodRepository =
        UserFoodRepositoryImpl(
            fireStore = fireStore,
            firebaseResource = firebaseResource,
            firebaseAuth = firebaseAuth
        )

    @Provides
    @Singleton
    fun provideUserDatabaseRepository(
        userDao: UserDao, roomDbResource: RoomDbResource
    ): UserDatabaseRepository =
        UserDatabaseRepositoryImpl(userDao = userDao, roomDbResource = roomDbResource)

    @Provides
    @Singleton
    fun provideFoodDatabaseRepository(
        foodDao: FoodDao, roomDbResource: RoomDbResource
    ): FoodDatabaseRepository =
        FoodDatabaseRepositoryImpl(foodDao = foodDao, roomDbResource = roomDbResource)
}