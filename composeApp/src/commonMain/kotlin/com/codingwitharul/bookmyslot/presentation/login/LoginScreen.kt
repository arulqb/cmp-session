package com.codingwitharul.bookmyslot.presentation.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.dont_have_account
import bookmyslot.composeapp.generated.resources.facebook
import bookmyslot.composeapp.generated.resources.google
import bookmyslot.composeapp.generated.resources.login_button
import bookmyslot.composeapp.generated.resources.login_illustration
import bookmyslot.composeapp.generated.resources.login_title
import bookmyslot.composeapp.generated.resources.logo
import bookmyslot.composeapp.generated.resources.logo_bml
import bookmyslot.composeapp.generated.resources.microsoft
import bookmyslot.composeapp.generated.resources.or_login_with
import bookmyslot.composeapp.generated.resources.pc
import bookmyslot.composeapp.generated.resources.phone_number
import bookmyslot.composeapp.generated.resources.sign_up
import bookmyslot.composeapp.generated.resources.twitter
import com.codingwitharul.bookmyslot.PermissionsViewModel
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.collectAsState
import org.koin.compose.koinInject
import com.codingwitharul.bookmyslot.presentation.login.LoginUiEvent
import com.codingwitharul.bookmyslot.presentation.login.LoginViewModel

@Preview
@Composable
fun LoginScreen(navController: NavController?) {
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

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
        Text(stringResource(Res.string.or_login_with), color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(modifier = Modifier.height(12.dp))
        // Social Buttons Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = { viewModel.onEvent(LoginUiEvent.OnFacebookLoginClick) }, enabled = !state.loading) {
                Icon(
                    painter = painterResource(Res.drawable.facebook),
                    contentDescription = stringResource(Res.string.facebook),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = { viewModel.onEvent(LoginUiEvent.OnGoogleLoginClick) }, enabled = !state.loading) {
                Icon(
                    painter = painterResource(Res.drawable.google),
                    contentDescription = stringResource(Res.string.google),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dp)
                )
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