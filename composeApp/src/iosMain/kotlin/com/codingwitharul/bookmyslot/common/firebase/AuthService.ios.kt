package com.codingwitharul.bookmyslot.common.firebase

import FirebaseCore
import FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSError
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Actual implementation of AuthService for iOS.
 * Uses the Firebase iOS SDK.
 */
actual class `AuthService.android` {

    private val _currentUser = MutableStateFlow<User?>(null)
    actual val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private var authStateDidChangeListenerHandle: NSObject? = null

    actual fun initialize() {
        // Initialize Firebase on iOS. This must be called once at app startup.
        // Make sure you've added FirebaseApp.configure() in your AppDelegate.swift
        // For example:
        // import FirebaseCore
        // @UIApplicationMain
        // class AppDelegate: UIResponder, UIApplicationDelegate {
        //     func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        //         FirebaseApp.configure()
        //         return true
        //     }
        // }
        println("Firebase Auth Service initialized on iOS.")

        // Set up the auth state listener
        authStateDidChangeListenerHandle = FirebaseAuth.Auth.auth().addAuthStateDidChangeListener { auth, user ->
            _currentUser.value = user?.toAppUser()
        }
    }

    actual suspend fun signInAnonymously(): User? {
        return suspendCoroutine { continuation ->
            FirebaseAuth.Auth.auth().signInAnonymouslyWithCompletion { authResult, error ->
                if (error != null) {
                    println("Error signing in anonymously on iOS: ${error.localizedDescription}")
                    continuation.resume(null)
                } else {
                    val user = authResult?.user?.toAppUser()
                    continuation.resume(user)
                }
            }
        }
    }

    actual suspend fun signOut() {
        return suspendCoroutine { continuation ->
            var error: NSError? = null
            try {
                FirebaseAuth.Auth.auth().signOut(&error)
                if (error != null) {
                    println("Error signing out on iOS: ${error.localizedDescription}")
                } else {
                    println("Signed out on iOS.")
                }
            } catch (e: Exception) {
                println("Exception signing out on iOS: ${e.message}")
            } finally {
                continuation.resume(Unit)
            }
        }
    }

    /**
     * Helper function to convert Firebase.User to our common User data class.
     */
    private fun FirebaseUser.toAppUser(): User {
        return User(uid = uid, isAnonymous = anonymous)
    }
}