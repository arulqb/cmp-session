package com.codingwitharul.bookmyslot.presentation.ui.ocr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codingwitharul.bookmyslot.common.CameraView

@Composable
fun OcrScreen() {
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            CameraView()
        }
    }
}