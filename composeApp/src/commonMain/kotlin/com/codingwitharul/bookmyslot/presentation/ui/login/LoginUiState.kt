package com.codingwitharul.bookmyslot.presentation.ui.login

import com.codingwitharul.bookmyslot.db.UserInfo

// UI State
data class LoginUiState(
    val loading: Boolean = false,
    val error: String = "",
    val isLoggedIn: Boolean = false,
    val userInfo: UserInfo? = null
)