package com.codingwitharul.bookmyslot.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.app_name
import bookmyslot.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppTopAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(Res.string.app_name)) },
        navigationIcon = {
            BackButton(onBackPressed = onBackPressed)
        })
}

@Composable
private fun BackButton(onBackPressed: () -> Unit) {
    IconButton(onClick = onBackPressed) {
        Icon(
            painter = painterResource(Res.drawable.logo),
            contentDescription = null
        )
    }
}