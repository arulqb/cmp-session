package com.codingwitharul.bookmyslot.common.firebase


import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual class AuthService {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow<User?>(null)
    actual val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener { auth ->
            _currentUser.value = auth.currentUser?.toAppUser()
        }
    }

    actual fun initialize() {

    }

    actual suspend fun signInAnonymously(): User? {
        return try {
            val result = firebaseAuth.signInAnonymously().await()
            result.user?.toAppUser()
        } catch (e: Exception) {
            println("Error signing in anonymously on Android: ${e.message}")
            null
        }
    }

    actual suspend fun signOut() {
        try {
            firebaseAuth.signOut()
            println("Signed out on Android.")
        } catch (e: Exception) {
            println("Error signing out on Android: ${e.message}")
        }
    }

    private fun FirebaseUser.toAppUser(): User {
        return User(uid = uid, isAnonymous = isAnonymous)
    }
}
