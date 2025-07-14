package com.codingwitharul.bookmyslot.data.repo

import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.data.networking.ApiClientHelper
import com.codingwitharul.bookmyslot.data.networking.EndPoints
import com.codingwitharul.bookmyslot.data.networking.apiEndPoint
import com.codingwitharul.bookmyslot.data.networking.models.ApiResponse
import com.codingwitharul.bookmyslot.data.networking.models.ErrorType
import com.codingwitharul.bookmyslot.data.networking.models.getErrorData
import com.codingwitharul.bookmyslot.data.networking.safeRequest
import com.codingwitharul.bookmyslot.data.toUserInfo
import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser
import com.codingwitharul.bookmyslot.utils.toThrowable
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod

class AuthRepoImpl(val db: DatabaseHelper, val apiClientHelper: ApiClientHelper) : AuthRepo {

    override suspend fun loginWithOAuthOnServer(user: GoogleUser): Result<UserInfo> {
        val resp = apiClientHelper.client.safeRequest<GoogleUser, GoogleUser> {
            apiEndPoint(EndPoints.Auth)
            method = HttpMethod.Post
            setBody(user)
        }
        return when(resp) {
            is ApiResponse.Error -> {
                Result.failure("Error: ${resp.getErrorData(ErrorType.LOGIN)}".toThrowable())
            }
            is ApiResponse.Success<GoogleUser> -> {
                val userInfo = resp.body.toUserInfo()
                db.saveUserInfo(userInfo)
                Result.success(userInfo)
            }
        }
    }

    override suspend fun getSavedUserInfo(): Result<UserInfo> {
        val user = db.getActiveUserInfo()
        if (user == null) {
            return Result.failure("No user found".toThrowable())
        } else {
            return Result.success(user)
        }
    }


    override suspend fun sendVerificationCode(phone: String): Result<String> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

    override suspend fun verifyCode(verificationId: String, code: String): Result<Unit> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

}
