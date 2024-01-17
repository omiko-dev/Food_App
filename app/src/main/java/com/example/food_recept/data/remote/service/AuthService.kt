package com.example.food_recept.data.remote.service

import com.example.food_recept.data.remote.model.login.LoginRequestDto
import com.example.food_recept.data.remote.model.register.RegisterRequestDto
import com.example.food_recept.data.remote.model.token.TokenDto
import com.example.food_recept.data.remote.model.user.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("Auth/Login")
    suspend fun logInService(@Body loginRequestDto: LoginRequestDto): Response<TokenDto>

    @POST("Auth/Register")
    suspend fun registerService(@Body registerRequestDto: RegisterRequestDto): Response<Unit>
}