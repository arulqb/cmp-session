package com.codingwitharul.bookmyslot.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepo: AuthRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnGoogleLoginClick -> {
                loginWithGoogle()
            }
            is LoginUiEvent.OnFacebookLoginClick -> {
                loginWithFacebook()
            }
            is LoginUiEvent.OnOAuthTokenReceived -> {
                _uiState.value = _uiState.value.copy(loading = true, error = "")
                viewModelScope.launch {
                    if (event.data != null) {
                        val result = authRepo.loginWithOAuthOnServer(event.data)
                        if (result.isSuccess) {
                            _uiState.value = _uiState.value.copy(loading = false, isLoggedIn = true, userInfo = result.getOrNull())
                        } else {
                            _uiState.value = _uiState.value.copy(loading = false, error = result.exceptionOrNull()?.message ?: "Server login failed")
                        }
                    } else {
                        _uiState.value = _uiState.value.copy(loading = false, error = "Google login failed")
                    }
                }
            }
            is LoginUiEvent.OnLoginSuccess -> {
                _uiState.value = _uiState.value.copy(loading = false, isLoggedIn = true, error = "")
            }
            is LoginUiEvent.OnLoginError -> {
                _uiState.value = _uiState.value.copy(loading = false, error = event.error)
            }
        }
    }

    private fun loginWithGoogle() {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {

            // onEvent(LoginUiEvent.OnOAuthTokenReceived("google", idToken))
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }

    private fun loginWithFacebook() {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            // On success, get accessToken and call:
            // onEvent(LoginUiEvent.OnOAuthTokenReceived("facebook", accessToken))
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }
} 