package com.codingwitharul.bookmyslot

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingwitharul.bookmyslot.presentation.ui.booking.BookingScreen
import com.codingwitharul.bookmyslot.presentation.ui.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.ui.pokedex.PokedexScreen
import com.codingwitharul.bookmyslot.presentation.ui.splash.SplashScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.Splash.name) {
        composable(AppRoutes.Splash.name) {
            SplashScreen { userInfo ->
                if (userInfo != null) {
                    navController.toBooking()
                } else {
                    navController.toLogin()
                }
            }
        }
        composable(AppRoutes.Login.name) { LoginScreen(navController) }
        composable(AppRoutes.Pokedex.name) { PokedexScreen() }
        composable(AppRoutes.Booking.name) { BookingScreen() }
    }
}

sealed class AppRoutes(val name: String) {
    object Splash : AppRoutes("splash")
    object Login : AppRoutes("login")
    object Pokedex : AppRoutes("pokedex")
    object Booking : AppRoutes("booking")
}



fun NavController.toBooking(popUpToRoute: AppRoutes? = AppRoutes.Booking, inclusiveRoute: Boolean = true) {
    this.navigate(AppRoutes.Booking.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}

fun NavController.toLogin(popUpToRoute: AppRoutes? = AppRoutes.Login, inclusiveRoute: Boolean = true) {
    this.navigate(AppRoutes.Login.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}