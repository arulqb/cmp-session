package com.codingwitharul.bookmyslot.common.auth

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {

    @Composable
    fun getUiProvider(): GoogleAuthenticator

    suspend fun signOut()
}