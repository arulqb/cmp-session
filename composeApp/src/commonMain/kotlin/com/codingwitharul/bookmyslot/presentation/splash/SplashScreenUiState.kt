package com.codingwitharul.bookmyslot.presentation.splash

import com.codingwitharul.bookmyslot.db.UserInfo

data class SplashScreenUiState(
    val userInfo: UserInfo?,
    val loading: Boolean = true
)