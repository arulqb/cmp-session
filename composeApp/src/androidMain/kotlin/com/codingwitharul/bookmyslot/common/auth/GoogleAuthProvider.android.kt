package com.codingwitharul.bookmyslot.common.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth

actual class GoogleAuthProvider(val credentialManager: CredentialManager) {
    @Composable
    actual fun getUiProvider(): GoogleAuthenticator {
        val activityContext = LocalContext.current
        return GoogleAuthenticator(activityContext, credentialManager = credentialManager)
    }

    actual suspend fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}