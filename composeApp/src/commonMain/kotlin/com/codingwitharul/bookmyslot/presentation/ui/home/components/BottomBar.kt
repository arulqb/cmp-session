package com.codingwitharul.bookmyslot.presentation.ui.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.bookmark
import bookmyslot.composeapp.generated.resources.home
import bookmyslot.composeapp.generated.resources.profile
import bookmyslot.composeapp.generated.resources.search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: DrawableResource
) {
    object Discover : BottomBarScreen("discover", "Discover", Res.drawable.home)
    object Book : BottomBarScreen("book", "Book", Res.drawable.bookmark)
    object History : BottomBarScreen("history", "History", Res.drawable.search)
    object Profile : BottomBarScreen("profile", "Profile", Res.drawable.profile)
}

val bottomBarScreens = listOf(
    BottomBarScreen.Discover,
    BottomBarScreen.Book,
    BottomBarScreen.History,
    BottomBarScreen.Profile
)

@Composable
fun AppBottomBar(
    currentRoute: BottomBarScreen?,
    onItemSelected: (BottomBarScreen) -> Unit
) {
    NavigationBar {
        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen,
                onClick = { onItemSelected(screen) },
                icon = { Icon(painterResource(screen.icon), contentDescription = screen.title) },
                label = { Text(screen.title) }
            )
        }
    }
}

@Preview()
@Composable
fun AppBottomBarPreview() {
    var currentRoute by remember { mutableIntStateOf(0) }
    AppBottomBar(
        currentRoute = bottomBarScreens[currentRoute],
        onItemSelected = { route -> currentRoute = bottomBarScreens.indexOfFirst { it == route } }
    )
}