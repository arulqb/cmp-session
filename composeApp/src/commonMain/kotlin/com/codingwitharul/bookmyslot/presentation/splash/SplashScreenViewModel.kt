package com.codingwitharul.bookmyslot.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(val authRepo: AuthRepo): ViewModel() {
    private val _uiState = MutableStateFlow(SplashScreenUiState(null))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = authRepo.getSavedUserInfo()
            Napier.d("User Received $user")
            _uiState.value = SplashScreenUiState(user.getOrNull(), false)
        }
    }
}