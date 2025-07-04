package com.codingwitharul.bookmyslot.presentation.login

import com.codingwitharul.bookmyslot.presentation.components.GoogleUser

sealed class LoginUiEvent {
    object OnGoogleLoginClick : LoginUiEvent()
    object OnFacebookLoginClick : LoginUiEvent()
    data class OnOAuthTokenReceived(val data: GoogleUser?) : LoginUiEvent()
    object OnLoginSuccess : LoginUiEvent()
    data class OnLoginError(val error: String) : LoginUiEvent()
}