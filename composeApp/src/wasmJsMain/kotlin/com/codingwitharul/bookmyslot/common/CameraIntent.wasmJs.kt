package com.codingwitharul.bookmyslot.common

actual class CameraController {
    actual fun openCamera() {
        // Initialize AVCaptureSession or present UIImagePickerController
    }

    actual fun captureImage(callback: (ByteArray) -> Unit) {
        // Capture image and pass it as ByteArray
    }
}