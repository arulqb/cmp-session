package com.codingwitharul.bookmyslot.common

expect class CameraController {
    fun openCamera()
    fun captureImage(callback: (ByteArray) -> Unit)
}