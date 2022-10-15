package com.ahmednmahran.common.data.model

import com.github.ahmednmahran.common.model.ChatUser
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val body: String="", val sender : ChatUser,)
