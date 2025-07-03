package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.data.model.UserInfo

interface AuthRepo {
    suspend fun sendVerificationCode(phone: String): Result<String> // returns verificationId
    suspend fun verifyCode(verificationId: String, code: String): Result<Unit>
    suspend fun loginWithOAuthOnServer(provider: String, token: String): Result<UserInfo>
} 