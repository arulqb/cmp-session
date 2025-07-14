package com.codingwitharul.bookmyslot.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.app_name
import bookmyslot.composeapp.generated.resources.profile2
import bookmyslot.composeapp.generated.resources.stallion_beatsides_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnBoardScreen(
    windowSizeClass: WindowSizeClass,
    onClickCapture: () -> Unit,
    onClickGallery: () -> Unit
) {

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome to")
            Text(
                stringResource(Res.string.app_name), fontFamily = FontFamily(
                    Font(
                        resource = Res.font.stallion_beatsides_regular,
                        weight = FontWeight.Normal,
                        style = FontStyle.Normal
                    )
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 58.sp
            )
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 200.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Image(painterResource(Res.drawable.profile2), "content description")
            }
            Text("Please select an option to continue")
            Button(onClick = {}, shape = RoundedCornerShape(6.dp)) {
                Text("Capture")
            }
            OutlinedButton(onClick = {}, shape = RoundedCornerShape(6.dp)) {
                Text("Pick From Gallery")
            }
        }
    }
}