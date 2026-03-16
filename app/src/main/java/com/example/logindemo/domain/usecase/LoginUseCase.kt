package com.example.logindemo.domain.usecase

import com.example.logindemo.domain.model.LoginRequest
import com.example.logindemo.domain.model.LoginResponse
import com.example.logindemo.domain.repository.AuthRepository
import com.example.logindemo.domain.utils.APIResult
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(request: LoginRequest) : Flow<APIResult<LoginResponse>>{
        return authRepository.login(request)
    }
}