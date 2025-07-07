package com.codingwitharul.bookmyslot.data

import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.presentation.components.GoogleUser
import kotlinx.datetime.Clock

fun GoogleUser.toUserInfo(): UserInfo {
    val data = this
    val now = Clock.System.now().epochSeconds
    return UserInfo(
        userId = data.uid,
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
}