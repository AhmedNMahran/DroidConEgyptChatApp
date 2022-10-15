package com.ahmednmahran.common.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val body: String="", val sender : String="",)
