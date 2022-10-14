package com.ahmednmahran.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmednmahran.common.ui.loginscreen.LoginScreen

@Composable
fun App(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("Hello, DroidConEgypt!") }
    val platformName = getPlatformName()
    Box {

        LoginScreen(
            modifier.padding(32.dp).wrapContentSize(Alignment.Center).background(
                color = Color(204, 153, 255),
                shape = RoundedCornerShape(16.dp)
            )
        ) { chatUser ->
        }
    }
}
