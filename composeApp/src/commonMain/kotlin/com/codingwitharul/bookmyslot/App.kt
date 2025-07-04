package com.codingwitharul.bookmyslot

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingwitharul.bookmyslot.di.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.codingwitharul.bookmyslot.presentation.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.pokedex.PokedexScreen
import com.codingwitharul.bookmyslot.presentation.splash.SplashScreen
import com.codingwitharul.bookmyslot.presentation.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App(koinAppDeclaration: KoinAppDeclaration? = null) {
    KoinApplication(application = {
        koinAppDeclaration?.invoke(this)
        modules(appModule())
    }) {
        AppTheme {
            Router()
        }
    }
}
