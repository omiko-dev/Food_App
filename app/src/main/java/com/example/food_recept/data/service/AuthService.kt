package com.example.food_recept.data.service

import com.example.food_recept.data.model.login.LoginRequestDto
import com.example.food_recept.data.model.user.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

//https://localhost:7277/api/Auth/Register
interface AuthService {
    @POST("Auth/Register")
    suspend fun logInService(@Body loginRequestDto: LoginRequestDto): Response<UserDto>
}