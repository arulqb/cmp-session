package com.codingwitharul.bookmyslot

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingwitharul.bookmyslot.presentation.booking.BookingScreen
import com.codingwitharul.bookmyslot.presentation.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.pokedex.PokedexScreen
import com.codingwitharul.bookmyslot.presentation.splash.SplashScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.Login.name) {
        composable(AppRoutes.Splash.name) { SplashScreen(navController) }
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
    }
}