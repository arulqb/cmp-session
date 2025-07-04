package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser

interface AuthRepo {
    suspend fun sendVerificationCode(phone: String): Result<String> // returns verificationId
    suspend fun verifyCode(verificationId: String, code: String): Result<Unit>
    suspend fun loginWithOAuthOnServer(user: GoogleUser): Result<UserInfo>
    suspend fun getSavedUserInfo(): Result<UserInfo>
} 