package com.ahmednmahran.common.data

import com.ahmednmahran.common.data.network.Network
import com.ahmednmahran.common.data.network.Network.client
import com.github.ahmednmahran.common.model.ChatUser
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LoginRepository {
    private var _user = MutableStateFlow<ChatUser>(ChatUser("","",""))
    val user = _user
    fun login(user: ChatUser) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = client.config {
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(
                                username = user.username,
                                password = user.password
                            )
                        }
                        realm = "Access to the '/' path"
                    }
                }
            }.post("login") {
                host = Network.host
                port = 8080
            }
            println("chatResponse:" + response.bodyAsText())
            _user.emit(Json.decodeFromString(response.bodyAsText()))
        }
    }
}