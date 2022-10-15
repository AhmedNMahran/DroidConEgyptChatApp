package com.ahmednmahran.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

expect fun getPlatformName(): String


expect fun getHost(): String

@Composable
expect fun ProfileImage(modifier: Modifier, url: String)