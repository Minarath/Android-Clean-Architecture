package com.example.logindemo.data.remote

import com.example.logindemo.domain.model.LoginRequest
import com.example.logindemo.domain.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}