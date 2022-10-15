package com.ahmednmahran.common.data


import com.ahmednmahran.common.data.model.ChatMessage
import com.ahmednmahran.common.data.network.Network
import com.ahmednmahran.common.data.model.ChatUser
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class ChatRepository(
    private val chatUser: ChatUser
) {

    private val _chatMessage = MutableStateFlow(ChatMessage("", ChatUser("", "", "")))
    val chatMessage: StateFlow<ChatMessage> = _chatMessage

    private var _alert = MutableStateFlow("")
    val alert: StateFlow<String> = _alert

    private var _user = MutableStateFlow<ChatUser>(ChatUser("", "", ""))
    val user = _user

    private val _job by lazy {
        CoroutineScope(Dispatchers.Default).launch {
            connect()
            startChat()
        }

    }
    private var _session: WebSocketSession? = null

    init {
        if (!_job.isActive)
            _job.start()
    }

    private suspend fun connect() {
        _session = Network.client.webSocketSession(
            method = HttpMethod.Get,
            host = Network.host,
            port = 8080,
            path = "/chat"
        )
        _user.emit(chatUser)
        println(chatUser.toString())
    }

    private suspend fun startChat() {
        try {
            receive()
        } catch (e: Exception) {
            if (e is ClosedReceiveChannelException) {
                _alert.emit("Disconnected. ${e.message}.")
            } else if (e is WebSocketException) {
                _alert.emit("Unable to connect.")
            }
            withTimeout(5000) {
                CoroutineScope(Dispatchers.Default).launch { startChat() }
            }
        }
    }

    fun send(message: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _session?.send(Frame.Text(message))
        }
    }


    private suspend fun receive() {
        while (true) {
            val frame = _session?.incoming?.receive()
            if (frame is Frame.Text) {
                extractChatMessage(frame.readText())
            }
        }
    }

    private suspend fun extractChatMessage(it: String) {
        println("extract: $it")
        try {
            _chatMessage.emit(Json.decodeFromString(it))
            _alert.emit("")
        } catch (th: Throwable) {
            _alert.emit(it)
        }
    }


}