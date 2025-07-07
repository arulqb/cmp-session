package com.codingwitharul.bookmyslot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {

        App {
                FirebaseApp.initializeApp(this@MainActivity)
                androidContext(this@MainActivity)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}