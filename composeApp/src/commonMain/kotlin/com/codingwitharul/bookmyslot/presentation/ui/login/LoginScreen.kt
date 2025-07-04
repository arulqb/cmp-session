package com.codingwitharul.bookmyslot.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.app_name
import bookmyslot.composeapp.generated.resources.dont_have_account
import bookmyslot.composeapp.generated.resources.facebook
import bookmyslot.composeapp.generated.resources.google
import bookmyslot.composeapp.generated.resources.login_illustration
import bookmyslot.composeapp.generated.resources.logo_bml
import bookmyslot.composeapp.generated.resources.or_login_with
import bookmyslot.composeapp.generated.resources.sign_up
import bookmyslot.composeapp.generated.resources.stallion_beatsides_regular
import com.codingwitharul.bookmyslot.presentation.components.GoogleButtonUiContainer
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser
import com.codingwitharul.bookmyslot.toBooking
import kotlinx.coroutines.flow.filter
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Preview
@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiState) {
        viewModel.uiState.filter { it.isLoggedIn }
            .collect {
                showToast("Logged In")
                navController.toBooking()
            }
    }

    fun onReceiveOAuth(result: Result<GoogleUser?>) {
        result.onFailure {
            showToast(it.message ?: "Failed")
        }.onSuccess {
            viewModel.onEvent(LoginUiEvent.OnOAuthTokenReceived(it))
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Illustration/Image
            Image(
                painter = painterResource(Res.drawable.logo_bml),
                contentDescription = stringResource(Res.string.login_illustration),
                modifier = Modifier.size(150.dp),
            )

            Text(
                stringResource(Res.string.app_name), fontFamily = FontFamily(
                    Font(
                        resource = Res.font.stallion_beatsides_regular,
                        weight = FontWeight.Normal,
                        style = FontStyle.Normal
                    )
                ),
                color = MaterialTheme.colorScheme.background,
                fontSize = 58.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
            if (state.loading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (state.error.isNotEmpty()) {
                Text(state.error, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (state.isLoggedIn) {
                Text("Login Successful!")
                Spacer(modifier = Modifier.height(8.dp))
            }
            // Or login with
            Text(
                stringResource(Res.string.or_login_with),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Social Buttons Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick = { viewModel.onEvent(LoginUiEvent.OnFacebookLoginClick) },
                    enabled = !state.loading
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.facebook),
                        contentDescription = stringResource(Res.string.facebook),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
                GoogleButtonUiContainer(onGoogleSignInResult = ::onReceiveOAuth) {
                    IconButton(onClick = { this.onClick() }, enabled = !state.loading) {
                        Icon(
                            painter = painterResource(Res.drawable.google),
                            contentDescription = stringResource(Res.string.google),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            // Sign up prompt
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(Res.string.dont_have_account) + " ")
                TextButton(onClick = { /* Handle sign up */ }) {
                    Text(stringResource(Res.string.sign_up))
                }
            }
        }
    }
}