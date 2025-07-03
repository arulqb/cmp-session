package com.codingwitharul.bookmyslot.presentation.login

sealed class LoginUiEvent {
    object OnGoogleLoginClick : LoginUiEvent()
    object OnFacebookLoginClick : LoginUiEvent()
    data class OnOAuthTokenReceived(val provider: String, val token: String) : LoginUiEvent()
    object OnLoginSuccess : LoginUiEvent()
    data class OnLoginError(val error: String) : LoginUiEvent()
}