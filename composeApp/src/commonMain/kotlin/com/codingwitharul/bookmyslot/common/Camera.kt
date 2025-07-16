package com.codingwitharul.bookmyslot.common

import androidx.compose.runtime.Composable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.io.files.Path

abstract class CameraCallback {

    sealed class CameraEvent {
        object Init : CameraEvent()
        object CaptureImage : CameraEvent()

        object SwitchCamera : CameraEvent()
    }

    private val _event = Channel<CameraEvent>()
    private val event = _event.receiveAsFlow()

    suspend fun sendEvent(event: CameraEvent) {
        this._event.send(event)
    }

    fun observeEvent(): Flow<CameraEvent> {
        return event
    }

    abstract fun onCaptureImage(image: Path?)
}

@Composable
expect fun CameraView(callback: CameraCallback)