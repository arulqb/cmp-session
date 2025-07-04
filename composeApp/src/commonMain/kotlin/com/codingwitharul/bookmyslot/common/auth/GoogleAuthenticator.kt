package com.codingwitharul.bookmyslot.common.auth

import androidx.compose.runtime.Composable
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser

expect class GoogleAuthenticator {
    suspend fun login(): Result<GoogleUser?>
}