package com.ahmednmahran.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.ahmednmahran.common.data.model.ChatUser
import com.ahmednmahran.common.ui.ProfileInfo


/**
 * The main screen to show chat between different users
 */
@Composable
fun ChatScreen(user: MutableState<ChatUser>) {
    Column {
        ProfileInfo(user)

    }

}