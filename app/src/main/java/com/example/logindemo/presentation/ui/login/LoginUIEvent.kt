package com.example.logindemo.presentation.ui.login

sealed class LoginUiEvent {
    object LoginSuccess: LoginUiEvent()
    data class ShowSnackBar(val message: String): LoginUiEvent()
}