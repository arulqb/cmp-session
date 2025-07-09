package com.codingwitharul.bookmyslot.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppTopAppBar(
    title: String?, navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    val bottomCornerRadius = 16.dp // Adjust this value to change the curve radius
    TopAppBar(
        title = { title?.let { Text(it, color = Color.White) } },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        actions = actions,
        navigationIcon = navigationIcon,
        modifier = Modifier.clip(
            RoundedCornerShape(bottomStart = bottomCornerRadius, bottomEnd = bottomCornerRadius)
        )
    )
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