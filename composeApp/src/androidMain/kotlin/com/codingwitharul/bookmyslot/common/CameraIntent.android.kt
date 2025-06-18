package com.codingwitharul.bookmyslot.common

import android.content.Context

actual class CameraController(private val context: Context) {
    actual fun openCamera() {
        // Launch Camera using CameraX or Intent
    }

    actual fun captureImage(callback: (ByteArray) -> Unit) {
        // Capture image and return byte array via callback
    }
}