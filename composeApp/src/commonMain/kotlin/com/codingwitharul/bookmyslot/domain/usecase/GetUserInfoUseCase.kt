package com.codingwitharul.bookmyslot.domain.usecase

import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo

class GetUserInfoUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(): Result<UserInfo> {
        return repo.getSavedUserInfo()
    }
}