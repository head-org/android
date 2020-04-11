package com.head.covidapp.data.repositories

import com.head.covidapp.data.datasources.MessageApiDataSource
import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.domain.repositories.MessageRepository

class MessageRepositoryImpl(private val messageApiDataSource: MessageApiDataSource) :
    MessageRepository {

    override suspend fun getMessages(): Response<List<MessageModel>> =
        messageApiDataSource.getMessages()

    override suspend fun postMessage(
        message: Pair<String, String>,
        userLocation: Pair<Double, Double>
    ): Response<MessageModel>  = messageApiDataSource.postMessage(message, userLocation)
}
