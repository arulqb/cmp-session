package com.codingwitharul.bookmyslot.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.presentation.ui.home.components.AppBottomBar
import com.codingwitharul.bookmyslot.presentation.ui.home.components.BottomBarScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.DiscoverScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.HistoryScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.ProfileScreen
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking.BookingScreen

@Composable
fun HomeScreen(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteString = navBackStackEntry?.destination?.route

    val currentRoute = remember(currentRouteString) {
        when (currentRouteString) {
            BottomBarScreen.Discover.route -> BottomBarScreen.Discover
            BottomBarScreen.History.route -> BottomBarScreen.History
            BottomBarScreen.Book.route -> BottomBarScreen.Book
            BottomBarScreen.Profile.route -> BottomBarScreen.Profile
            else -> BottomBarScreen.Discover // Default or handle error
        }
    }

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onItemSelected = { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Discover.route
            ) {
                composable(BottomBarScreen.Discover.route) { DiscoverScreen() }
                composable(BottomBarScreen.History.route) { HistoryScreen() }
                composable(BottomBarScreen.Book.route) { BookingScreen() }
                composable(BottomBarScreen.Profile.route) { ProfileScreen() }
            }
        }
    }
}