package com.codingwitharul.bookmyslot.presentation.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.codingwitharul.bookmyslot.common.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data class GoogleUser(
    val token: String,
    val uid: String,
    val name: String?,
    val profilePictureUrl: String?,
    val email: String? = null,
    val phoneNumber: String? = null
)

interface GoogleButtonUiContainerScope {
    fun onClick()
}

@Composable
fun GoogleButtonUiContainer(
    modifier: Modifier = Modifier,
    onGoogleSignInResult: (Result<GoogleUser?>) -> Unit,
    content: @Composable GoogleButtonUiContainerScope.() -> Unit,
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()

    val uiContainerScope = remember {
        object : GoogleButtonUiContainerScope {
            override fun onClick() {
                coroutineScope.launch {
                    val googleUser = googleAuthUiProvider.login()
                    onGoogleSignInResult(googleUser)
                }
            }
        }
    }
    Surface(
        modifier = modifier,
        content = { uiContainerScope.content() }
    )
}