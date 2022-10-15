package com.ahmednmahran.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ahmednmahran.common.ProfileImage
import com.github.ahmednmahran.common.model.ChatUser

/**
 * composable to show Profile Info
 */
@Composable
fun ProfileInfo(user: MutableState<ChatUser>) {
    Card(elevation = 16.dp, modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(Modifier.clip(CircleShape), user.value.profileImageUrl!!)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = user.value.username,
                style = TextStyle(color = MaterialTheme.colors.primary),
            )
        }
    }

}