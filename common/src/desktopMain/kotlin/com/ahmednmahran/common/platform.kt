package com.ahmednmahran.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.jetbrains.skia.Image

actual fun getPlatformName(): String {
    return "Desktop"
}

actual fun getHost(): String {
    return "127.0.0.1"
}

@Composable
actual fun ProfileImage(modifier: Modifier, url: String) {
    println("desktop image")
    val image = remember(url) { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(url) {
        HttpClient().get(url).let {
            image.value = Image.makeFromEncoded(it.body()).toComposeImageBitmap()
        }
    }
    if (image.value != null) {
        Image(
            bitmap = image.value!!,
            contentDescription = null,
            modifier = Modifier,
            contentScale = ContentScale.Fit
        )
    }
}