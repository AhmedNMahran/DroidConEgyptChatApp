package com.ahmednmahran.common.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.clip
import com.ahmednmahran.common.ProfileImage
import com.github.ahmednmahran.common.model.ChatUser
import androidx.compose.ui.Modifier


/**
 * The main screen to show chat between different users
 */
@Composable
fun ChatScreen(user: MutableState<ChatUser>) {
    Column {
        ProfileImage(Modifier.clip(CircleShape), user.value.profileImageUrl!!)
    }

}