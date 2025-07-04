package com.codingwitharul.bookmyslot.common.auth

import android.credentials.ClearCredentialStateRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth

actual class GoogleAuthProvider() {
    @Composable
    actual fun getUiProvider(): GoogleAuthenticator {
        val activityContext = LocalContext.current
        return GoogleAuthenticator(activityContext, CredentialManager.create(activityContext))
    }

    actual suspend fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}