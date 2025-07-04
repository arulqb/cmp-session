package com.codingwitharul.bookmyslot.data.repo

import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.data.networking.ApiClientHelper
import com.codingwitharul.bookmyslot.data.networking.EndPoints
import com.codingwitharul.bookmyslot.data.networking.apiEndPoint
import com.codingwitharul.bookmyslot.data.networking.models.ApiResponse
import com.codingwitharul.bookmyslot.data.networking.models.ErrorType
import com.codingwitharul.bookmyslot.data.networking.models.getErrorData
import com.codingwitharul.bookmyslot.data.networking.safeRequest
import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser
import com.codingwitharul.bookmyslot.utils.toThrowable
import io.github.aakira.napier.Napier
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class AuthRepoImpl(val db: DatabaseHelper, val apiClientHelper: ApiClientHelper) : AuthRepo {

    override suspend fun sendVerificationCode(phone: String): Result<String> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

    override suspend fun verifyCode(verificationId: String, code: String): Result<Unit> {
        // TODO: Implement with Firebase SDK in platform-specific code
        return Result.failure(NotImplementedError("Platform-specific implementation required"))
    }

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
                val data = resp.body
                val now = Clock.System.now().epochSeconds
                val userInfo = UserInfo(
                    userId = "",
                    userName = data.name,
                    email = data.email,
                    phoneNumber = data.phoneNumber,
                    photoUri = data.profilePictureUrl,
                    authToken = data.token,
                    providerId = "google",
                    displayName = data.name,
                    bio = null,
                    dateOfBirth = null,
                    lastLoginAt = now,
                    createdAt = now,
                    updatedAt = now,
                    isLoggedIn = true
                )
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
}
