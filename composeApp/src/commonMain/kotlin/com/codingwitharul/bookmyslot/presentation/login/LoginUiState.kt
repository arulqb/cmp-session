package com.codingwitharul.bookmyslot.presentation.login

import com.codingwitharul.bookmyslot.data.model.UserInfo

// UI State
data class LoginUiState(
    val loading: Boolean = false,
    val error: String = "",
    val isLoggedIn: Boolean = false,
    val userInfo: UserInfo? = null
)