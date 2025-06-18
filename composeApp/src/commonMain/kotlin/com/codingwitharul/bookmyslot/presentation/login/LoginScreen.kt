package com.codingwitharul.bookmyslot.presentation.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.pc
import com.codingwitharul.bookmyslot.PermissionsViewModel
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun LoginScreen(navController: NavController) {

    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) {
        factory.createPermissionsController()
    }

    BindEffect(controller)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val rotationState = remember { Animatable(0f) }

    val permissionsViewModel = viewModel {
        PermissionsViewModel(controller)
    }

    fun login() {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            navController.navigate("pokedex")
        } else {
            showToast("Please Enter Valid Email/Password")
        }
    }


    LaunchedEffect(Unit) {
        rotationState.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
        when(permissionsViewModel.state) {
            PermissionState.Granted -> {
                showToast("Camera permission is Granted")
            }
            PermissionState.DeniedAlways -> {

            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.pc), // Replace with your logo
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp).rotate(rotationState.value)
        )
        // Text(text = "Login", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (permissionsViewModel.state == PermissionState.Granted) {
                login()
            } else {
                permissionsViewModel.provideOrRequestCameraPermission()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }
        TextButton(onClick = { /* Handle forgot password */ }) {
            Text("Forgot Password?")
        }
        TextButton(onClick = { /* Handle registration */ }) {
            Text("Don't have an account? Register")
        }
    }
}