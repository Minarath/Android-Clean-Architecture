package com.example.logindemo.data.repoimpl

import com.example.logindemo.data.remote.AuthApi
import com.example.logindemo.domain.model.LoginRequest
import com.example.logindemo.domain.model.LoginResponse
import com.example.logindemo.domain.repository.AuthRepository
import com.example.logindemo.domain.utils.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(private val authApi: AuthApi) : AuthRepository {
    override fun login(request: LoginRequest): Flow<APIResult<LoginResponse>> = flow {
        emit(APIResult.Loading)
        try {
            val response = authApi.login(request)
            emit(APIResult.OnSuccess(response))

        } catch (e: Exception) {
            emit(APIResult.OnError("Something went wrong" + e.message))
        }
    }
}