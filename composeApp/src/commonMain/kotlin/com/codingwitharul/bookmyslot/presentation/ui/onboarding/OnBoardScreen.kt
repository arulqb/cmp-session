package com.codingwitharul.bookmyslot.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import bookmyslot.composeapp.generated.resources.Res
import bookmyslot.composeapp.generated.resources.app_name
import bookmyslot.composeapp.generated.resources.profile2
import bookmyslot.composeapp.generated.resources.stallion_beatsides_regular
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.codingwitharul.bookmyslot.common.rememberCameraManager
import com.codingwitharul.bookmyslot.common.rememberGalleryManager
import com.codingwitharul.bookmyslot.presentation.ui.onboarding.components.ImageCaptureView
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.io.files.Path
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnBoardScreen(
    windowSizeClass: WindowSizeClass
) {
    var showCamera by remember { mutableStateOf(false) }
    var imagePath by remember { mutableStateOf<Path?>(null) }
    var imageFromPicker by remember { mutableStateOf<ImageBitmap?>(null) }
    val scope = rememberCoroutineScope()
    fun onOpenCamera() {
        imagePath = null
        showCamera = true
    }

    val gallery = rememberGalleryManager { result ->
        scope.launch {
            imageFromPicker = withContext(Dispatchers.Default) {
                result?.toImageBitmap()
            }
        }
    }

    val cameraManager = rememberCameraManager {
        scope.launch {
            imageFromPicker = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
        }
    }

    fun onPickFromGallery() {
        gallery.launch()
    }

    fun onOpenDefaultCamera() {
        cameraManager.launch()
    }


    Scaffold {
        if (showCamera) {
            ImageCaptureView(onImageCaptured = {
                scope.launch {
                    showCamera = false
                    imagePath = it
                }
            }, onClose = { showCamera = false })
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Welcome to")

                Text(
                    stringResource(Res.string.app_name), fontFamily = FontFamily(
                        Font(
                            resource = Res.font.stallion_beatsides_regular,
                            weight = FontWeight.Normal,
                            style = FontStyle.Normal
                        )
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 58.sp
                )
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 200.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    if (imagePath != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalPlatformContext.current)
                                .data(imagePath!!.toString())
                                .build(),
                            contentDescription = "icon",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            onError = {
                                Napier.d("error loading image $it")
                            }
                        )
                    } else if (imageFromPicker != null) {
                        Image(
                            imageFromPicker!!, "content description",
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )
                    } else {
                        Image(painterResource(Res.drawable.profile2), "content description")
                    }
                }
                Text("Please select an option to continue")
                Button(onClick = ::onOpenCamera, shape = RoundedCornerShape(6.dp)) {
                    Text("Capture")
                }
                OutlinedButton(onClick = ::onPickFromGallery, shape = RoundedCornerShape(6.dp)) {
                    Text("Pick From Gallery")
                }
                OutlinedButton(onClick = ::onOpenDefaultCamera, shape = RoundedCornerShape(6.dp)) {
                    Text("Open Device Camera")
                }
            }
        }
    }
}