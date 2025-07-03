package com.codingwitharul.bookmyslot.common.auth

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthenticator = GoogleAuthenticator()


    actual suspend fun signOut() {

    }
}