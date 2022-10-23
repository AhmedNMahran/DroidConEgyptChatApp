package com.ahmednmahran.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

actual fun getPlatformName(): String {
    return "Android"
}
actual fun getHost(): String {
    return "10.0.2.2"
}

@Composable
actual fun ProfileImage(modifier: Modifier, url: String) {
    println("android image")
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}