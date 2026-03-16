package com.example.logindemo.domain.utils

sealed class APIResult<out T> {
    object Loading : APIResult<Nothing>()

    data class OnSuccess<T>(val data: T) : APIResult<T>()

    data class OnError(val errorMessage: String) : APIResult<Nothing>()
}