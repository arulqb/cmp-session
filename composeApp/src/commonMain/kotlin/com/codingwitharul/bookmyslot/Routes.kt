package com.codingwitharul.bookmyslot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.presentation.MainViewModel
import com.codingwitharul.bookmyslot.presentation.ui.home.HomeScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking.BookingScreen
import com.codingwitharul.bookmyslot.presentation.ui.login.LoginScreen
import com.codingwitharul.bookmyslot.presentation.ui.onboarding.OnBoardScreen
import com.codingwitharul.bookmyslot.presentation.ui.onboarding.components.ProfileImage
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
    NavHost(navController = navController, startDestination = AppRoutes.Home.name) {
        composable(AppRoutes.Splash.name) {
            SplashScreen(mainVm.timerState) { userInfo ->
                if (userInfo != null) {
                    navController.toHome()
                } else {
                    navController.toLogin()
                }
            }
        }
        composable(AppRoutes.Login.name) {
            LoginScreen(mainVm.timerState) {
                navController.toHome()
            }
        }
        composable(AppRoutes.Pokedex.name) { PokedexScreen() }
        composable(AppRoutes.Booking.name) { BookingScreen() }
        composable(AppRoutes.Home.name) { HomeScreen() }
        composable(AppRoutes.OnBoardScreen.name) {
            OnBoardScreen(
                windowSizeClass,
                onClickCapture = {
                    navController.navigate(AppRoutes.ProfileImage)
                },
                onClickGallery = {

                })
        }
        composable(AppRoutes.ProfileImage.name) { ProfileImage(windowSizeClass) }
    }
}

sealed class AppRoutes(val name: String) {
    object Splash : AppRoutes("splash")
    object Login : AppRoutes("login")
    object Pokedex : AppRoutes("pokedex")
    object Booking : AppRoutes("booking")
    object Home : AppRoutes("home")
    object OnBoardScreen : AppRoutes("OnBoardScreen")
    object ProfileImage : AppRoutes("ProfileImage")
}


fun NavController.toBooking(
    popUpToRoute: AppRoutes? = AppRoutes.Booking,
    inclusiveRoute: Boolean = true
) {
    this.navigate(AppRoutes.Booking.name) {
        popUpToRoute?.let {
            popUpTo(popUpToRoute.name) {
                inclusive = inclusiveRoute
            }
        }
        launchSingleTop = true
    }
}

fun NavHostController.navigate(routes: AppRoutes) {
    this.navigate(routes.name)
}

fun NavController.toLogin(
    popUpToRoute: AppRoutes? = AppRoutes.Login,
    inclusiveRoute: Boolean = true
) {
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