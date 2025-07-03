package com.codingwitharul.bookmyslot.data.repo

import com.codingwitharul.bookmyslot.data.model.UserInfo
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo

class AuthRepoImpl : AuthRepo {
    override suspend fun sendVerificationCode(phone: String): Result<String> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

    override suspend fun verifyCode(verificationId: String, code: String): Result<Unit> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

    override suspend fun loginWithOAuthOnServer(
        provider: String,
        token: String
    ): Result<UserInfo> {
        TODO("Not yet implemented")
    }
} 