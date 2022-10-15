package com.ahmednmahran.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmednmahran.common.data.LoginRepository
import com.ahmednmahran.common.ui.chat.ChatScreen
import com.ahmednmahran.common.ui.loginscreen.LoginScreen
import com.github.ahmednmahran.common.model.ChatUser

@Composable
fun App(modifier: Modifier = Modifier, loginRepository: LoginRepository = LoginRepository()) {
    val chatUser = loginRepository.user.collectAsState()
    Box {
        if (chatUser.value.username.isBlank()) {
            LoginScreen(
                modifier.padding(32.dp).wrapContentSize(Alignment.Center).background(
                    color = Color(204, 153, 255),
                    shape = RoundedCornerShape(16.dp)
                )
            ) { chatUser ->
                loginRepository.login(chatUser)

            }
        }
        else{
            ChatScreen(chatUser as MutableState<ChatUser>)
        }
    }
}
