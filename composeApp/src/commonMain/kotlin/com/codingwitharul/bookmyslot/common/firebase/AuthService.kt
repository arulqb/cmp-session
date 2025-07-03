package com.codingwitharul.bookmyslot.common.firebase

import kotlinx.coroutines.flow.StateFlow

data class User(val uid: String, val isAnonymous: Boolean)

expect class AuthService {
    val currentUser: StateFlow<User?>

    /**
     * Signs in the user anonymously.
     * @return The [User] object if successful, or null if an error occurred.
     */
    suspend fun signInAnonymously(): User?

    /**
     * Signs out the current user.
     */
    suspend fun signOut()

    /**
     * Provides a way to initialize the platform-specific Firebase SDKs.
     * This should be called once at the application startup on each platform.
     */
    fun initialize()
}