package com.example.logindemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.logindemo.data.remote.NetworkClient
import com.example.logindemo.data.repoimpl.AuthRepositoryImpl
import com.example.logindemo.domain.usecase.LoginUseCase
import com.example.logindemo.presentation.ui.login.LoginScreen
import com.example.logindemo.presentation.ui.login.LoginViewModel
import com.example.logindemo.presentation.ui.login.LoginViewModelFactory
import com.example.logindemo.ui.theme.LoginDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authApi = NetworkClient.authApi
        val authRepository = AuthRepositoryImpl(authApi)
        val loginUseCase = LoginUseCase(authRepository)
        val factory = LoginViewModelFactory(loginUseCase)
        val loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        setContent {
            LoginDemoTheme {
                LoginScreen(loginViewModel)
            }
        }
    }
}
