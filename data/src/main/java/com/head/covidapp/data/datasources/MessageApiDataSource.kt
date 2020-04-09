package com.head.covidapp.data.datasources

import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.models.message.MessageModel

interface MessageApiDataSource {

    suspend fun getMessages(): Response<List<MessageModel>>
}
