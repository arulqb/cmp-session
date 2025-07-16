package com.codingwitharul.bookmyslot.common

import androidx.compose.runtime.Composable

// GalleryManager.kt
@Composable
expect fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager


expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}