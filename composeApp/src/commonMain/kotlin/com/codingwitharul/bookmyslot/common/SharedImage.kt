package com.codingwitharul.bookmyslot.common

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.io.files.Path

expect class SharedImage {
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
    val path: Path?
}