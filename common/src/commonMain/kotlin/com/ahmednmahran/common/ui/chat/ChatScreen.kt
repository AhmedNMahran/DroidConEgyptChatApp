package com.ahmednmahran.common.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ahmednmahran.common.data.ChatRepository
import com.ahmednmahran.common.data.model.ChatMessage
import com.ahmednmahran.common.data.model.ChatUser
import com.ahmednmahran.common.ui.ProfileInfo


/**
 * The main screen to show chat between different users
 */
@Composable
fun ChatScreen(user: MutableState<ChatUser>, chatRepository:ChatRepository = remember {  ChatRepository(chatUser = user.value)}) {
    var alert = chatRepository.alert.collectAsState("")
    val list = remember {  mutableListOf<ChatMessage>()}
    chatRepository.chatMessage.collectAsState(ChatMessage("",
        ChatUser("","","")
    )).value.let {
        if(it.body.isNotBlank() && it.sender.username.isNotBlank())
            list.add(it)
    }

    Column {
        if(alert.value.isNotBlank()){
            Alert(alert.value)
        }
        ProfileInfo(user)
        LazyColumn(Modifier.weight(1f).background(MaterialTheme.colors.surface)) {
            items(items = list, itemContent = { chatMessage ->
                MessageCard(chatMessage,
                    if (chatMessage.sender.username == user.value.username)
                        Position.LEFT
                    else
                        Position.RIGHT
                )
            })
        }
        MessageComposer{
            chatRepository.send(ChatMessage(it,user.value))
        }
    }

}