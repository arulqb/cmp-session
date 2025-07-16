package com.codingwitharul.bookmyslot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.presentation.MainViewModel
import com.codingwitharul.bookmyslot.presentation.ui.home.HomeScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking.BookingScreen
import com.codingwitharul.bookmyslot.presentation.ui.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.ui.onboarding.OnBoardScreen
import com.codingwitharul.bookmyslot.presentation.ui.pokedex.PokedexScreen
import com.codingwitharul.bookmyslot.presentation.ui.splash.SplashScreen
import multiplatform.network.cmptoast.showToast

@Composable
fun Router(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    val mainVm = viewModel<MainViewModel>()
    val data by remember {
        derivedStateOf { mainVm.timerState.value > 30 }
    }
    LaunchedEffect(data) {
        showToast("Timer state is greater than 30")
    }

    NavHost(navController = navController, startDestination = AppRoutes.OnBoardScreen().route) {
        composable(AppRoutes.Splash.route) {
            SplashScreen(mainVm.timerState) { userInfo ->
                if (userInfo != null) {
                    navController.toHome()
                } else {
                    navController.toLogin()
                }
            }
        }
        composable(AppRoutes.Login.route) {
            LoginScreen(mainVm.timerState) {
                navController.toHome()
            }
        }
        composable(AppRoutes.Pokedex.route) { PokedexScreen() }
        composable(AppRoutes.Booking.route) { BookingScreen() }
        composable(AppRoutes.Home.route) { HomeScreen() }

        composable(
            route = AppRoutes.OnBoardScreen().route,
            arguments = listOf(navArgument("imagePath") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            OnBoardScreen(windowSizeClass)
        }
    }
}

sealed class AppRoutes(val route: String) {
    object Splash : AppRoutes("splash")
    object Login : AppRoutes("login")
    object Pokedex : AppRoutes("pokedex")
    object Booking : AppRoutes("booking")
    object Home : AppRoutes("home")
    data class OnBoardScreen(val imagePath: String? = null) :
        AppRoutes("OnBoardScreen/${imagePath ?: "{imagePath}"}")
    object ProfileImage : AppRoutes("ProfileImage")
}


fun NavController.toBooking(
    popUpToRoute: AppRoutes? = AppRoutes.Booking,
    inclusiveRoute: Boolean = true
) {
    this.navigate(AppRoutes.Booking.route) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.route) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}

fun NavHostController.navigate(routes: AppRoutes) {
    this.navigate(routes.route)
}

fun NavController.toLogin(
    popUpToRoute: AppRoutes? = AppRoutes.Login,
    inclusiveRoute: Boolean = true
) {
    this.navigate(AppRoutes.Login.route) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.route) {
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
    this.navigate(AppRoutes.Home.route) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.route) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}