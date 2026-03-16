package com.example.logindemo.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logindemo.domain.model.LoginRequest
import com.example.logindemo.domain.usecase.LoginUseCase
import com.example.logindemo.domain.utils.APIResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _loginUIEvent = MutableSharedFlow<LoginUiEvent>()
    val loginUiEvent: SharedFlow<LoginUiEvent> = _loginUIEvent.asSharedFlow()

    fun onUserNameChange(userName: String) {
        _uiState.value = _uiState.value.copy(email = userName)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onLoginClick() {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            viewModelScope.launch {
                _loginUIEvent.emit(
                    LoginUiEvent.ShowSnackBar("Username and Password can not be empty")
                )
            }
            return
        }

        viewModelScope.launch {
            loginUseCase(
                LoginRequest(
                    username = state.email, password = state.password
                )
            ).collect { result ->

                when (result) {
                    is APIResult.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true
                        )
                    }

                    is APIResult.OnError -> {
                         _uiState.value = _uiState.value.copy(
                             isLoading = false
                         )
                        _loginUIEvent.emit(LoginUiEvent.ShowSnackBar(message = result.errorMessage))
                    }

                    is APIResult.OnSuccess -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false, isLoginSuccess = true
                        )
                        _loginUIEvent.emit(LoginUiEvent.LoginSuccess)
                    }
                }
            }
        }
    }

}