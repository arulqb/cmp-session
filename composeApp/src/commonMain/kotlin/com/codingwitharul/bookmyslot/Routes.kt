package com.codingwitharul.bookmyslot

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.presentation.ui.home.HomeScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking.BookingScreen
import com.codingwitharul.bookmyslot.presentation.ui.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.ui.pokedex.PokedexScreen
import com.codingwitharul.bookmyslot.presentation.ui.splash.SplashScreen

@Composable
fun Router(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.Splash.name) {
        composable(AppRoutes.Splash.name) {
            SplashScreen { userInfo ->
                if (userInfo != null) {
                    navController.toHome()
                } else {
                    navController.toLogin()
                }
            }
        }
        composable(AppRoutes.Login.name) {
            LoginScreen {
                navController.toHome()
            }
        }
        composable(AppRoutes.Pokedex.name) { PokedexScreen() }
        composable(AppRoutes.Booking.name) { BookingScreen() }
        composable(AppRoutes.Home.name) { HomeScreen(windowSizeClass) }
    }
}

sealed class AppRoutes(val name: String) {
    object Splash : AppRoutes("splash")
    object Login : AppRoutes("login")
    object Pokedex : AppRoutes("pokedex")
    object Booking : AppRoutes("booking")
    object Home : AppRoutes("home")
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

fun NavController.toHome(
    popUpToRoute: AppRoutes? = AppRoutes.Home,
    inclusiveRoute: Boolean = true
) {
    this.navigate(AppRoutes.Home.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}