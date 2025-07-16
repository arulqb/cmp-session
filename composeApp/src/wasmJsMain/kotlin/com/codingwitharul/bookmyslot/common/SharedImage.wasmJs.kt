package com.codingwitharul.bookmyslot.common

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.io.files.Path

actual class SharedImage {
    actual fun toByteArray(): ByteArray? {
        TODO("Not yet implemented")
    }

    actual fun toImageBitmap(): ImageBitmap? {
        TODO("Not yet implemented")
    }

    actual val path: Path?
        get() = TODO("Not yet implemented")
}