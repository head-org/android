package com.head.miso.data.datasources

import com.head.miso.domain.arch.callback.Response
import com.head.miso.domain.models.message.MessageModel

interface MessageApiDataSource {

    suspend fun getMessages(): Response<List<MessageModel>>

    suspend fun postMessage(
        message: Pair<String, String>,
        userLocation: Pair<Double, Double>
    ): Response<MessageModel>
}
