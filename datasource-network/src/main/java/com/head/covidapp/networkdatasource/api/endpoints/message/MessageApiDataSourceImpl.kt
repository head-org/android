package com.head.covidapp.networkdatasource.api.endpoints.message

import com.head.covidapp.data.datasources.MessageApiDataSource
import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.arch.callback.callbackApiCall
import com.head.covidapp.domain.arch.callback.mapResponse
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.networkdatasource.api.clients.MainApiClient
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessageMapper
import retrofit2.create

class MessageApiDataSourceImpl(
    mainApiClient: MainApiClient,
    private val messageMapper: MessageMapper
) : MessageApiDataSource {

    private val service = mainApiClient.builder.create<MessageApiService>()

    override suspend fun getMessages(): Response<List<MessageModel>> =
        callbackApiCall {
            service.getMessages()
        }.mapResponse(messageMapper::apiModelToModel)
}
