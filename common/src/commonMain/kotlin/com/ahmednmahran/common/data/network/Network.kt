package com.ahmednmahran.common.data.network

import com.ahmednmahran.common.getHost
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*

object Network {
    val host: String = getHost()
    val client: HttpClient by lazy {
        HttpClient {
            install(WebSockets)
        }
    }

}