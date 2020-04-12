package com.head.miso.data.repositories

import com.head.miso.data.datasources.MessageApiDataSource
import com.head.miso.domain.arch.callback.Response
import com.head.miso.domain.models.message.MessageModel
import com.head.miso.domain.repositories.MessageRepository

class MessageRepositoryImpl(private val messageApiDataSource: MessageApiDataSource) :
    MessageRepository {

    override suspend fun getMessages(): Response<List<MessageModel>> =
        messageApiDataSource.getMessages()

    override suspend fun postMessage(
        message: Pair<String, String>,
        userLocation: Pair<Double, Double>
    ): Response<MessageModel>  = messageApiDataSource.postMessage(message, userLocation)
}
