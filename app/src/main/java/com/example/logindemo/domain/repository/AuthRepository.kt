package com.example.logindemo.domain.repository

import com.example.logindemo.domain.model.LoginRequest
import com.example.logindemo.domain.model.LoginResponse
import com.example.logindemo.domain.utils.APIResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(request: LoginRequest): Flow<APIResult<LoginResponse>>

}