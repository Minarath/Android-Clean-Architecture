package com.example.logindemo.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logindemo.R
import com.example.logindemo.presentation.components.EditTextField
import com.example.logindemo.presentation.components.PasswordTextField
import com.example.logindemo.presentation.components.SuccessDialog
import com.example.logindemo.ui.theme.componentColor
import com.example.logindemo.ui.theme.componentTextColor
import com.example.logindemo.ui.theme.mainBGColor

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loginUiEvent.collect { event ->
            when (event) {

                LoginUiEvent.LoginSuccess -> {
//                    snackBarHostState.showSnackbar("Login Success")
                    showDialog = true
                }

                is LoginUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .imePadding()
                    .navigationBarsPadding()
            )
        }) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainBGColor)
        ) {

            if (showDialog) {
                SuccessDialog(
                    title = "Login",
                    message = "Successful",
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        showDialog = false
                    })
            }

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.img_top_half_circle),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(130.dp))

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(250.dp),
                    painter = painterResource(R.drawable.img_login),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(40.dp))

                EditTextField(
                    value = state.email,
                    onValueChange = { viewModel.onUserNameChange(it) },
                    label = "Email",
                    leadingIcon = R.drawable.img_email
                )

                Spacer(modifier = Modifier.height(8.dp))

                PasswordTextField(
                    password = state.password
                ) {
                    viewModel.onPasswordChange(it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                ForgotPassword()

                Spacer(modifier = Modifier.height(40.dp))

                LoginButton(
                    enabled = !state.isLoading
                ) {
                    viewModel.onLoginClick()
                }

                Spacer(modifier = Modifier.height(20.dp))

                SignupText()
            }

            // Loading overlay
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun LoginButton(
    enabled: Boolean = true, onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        enabled = enabled,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = componentColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text("Login", color = componentTextColor)
    }
}

@Composable
fun ForgotPassword() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Forgot Password",
            fontWeight = FontWeight.Bold,
            color = componentTextColor,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SignupText() {
    Row {
        Text("Dont have account? ")
        Text(
            text = "Sign Up", color = componentTextColor, fontWeight = FontWeight.Bold
        )
    }
}