package com.codingwitharul.bookmyslot.domain.usecase

import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser

class LoginUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(googleUser: GoogleUser): Result<UserInfo> {
        return repo.loginWithOAuthOnServer(googleUser)
    }
}