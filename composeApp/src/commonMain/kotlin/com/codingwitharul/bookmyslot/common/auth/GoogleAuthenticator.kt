package com.codingwitharul.bookmyslot.common.auth

import androidx.compose.runtime.Composable

expect class GoogleAuthenticator {
    suspend fun login(): String?
}