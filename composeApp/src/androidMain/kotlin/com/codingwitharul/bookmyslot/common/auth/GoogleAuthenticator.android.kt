package com.codingwitharul.bookmyslot.common.auth


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

actual class GoogleAuthenticator(
    private val context: Context,
    private val credentialManager: CredentialManager
) {

    actual suspend fun login(): String? {
        try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId("520863704996-ovcrdlchfn3uimu8ddojcnh5hc678q6j.apps.googleusercontent.com")
                .setAutoSelectEnabled(false)
                .setFilterByAuthorizedAccounts(false)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                return idToken
//                val authCredential = GoogleAuthProvider.getCredential(idToken, null)
//                val fireBase = FirebaseAuth.getInstance()
//                val user = fireBase.signInWithCredential(authCredential).await().user
//                return user?.displayName.toString()
            }
            return "SOmething went wrong"
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            return "No email found in your device"
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            return "GetCredentialException"
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

}