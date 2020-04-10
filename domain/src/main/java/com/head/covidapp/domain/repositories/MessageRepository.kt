package com.head.covidapp.domain.repositories

import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.models.message.MessageModel

interface MessageRepository {

    suspend fun getMessages(): Response<List<MessageModel>>
}
