package com.codingwitharul.bookmyslot.presentation.ui.onboarding.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.common.CameraView

@Composable
fun ProfileImage(windowSizeClass: WindowSizeClass) {
    Box {
        CameraView()
    }
}