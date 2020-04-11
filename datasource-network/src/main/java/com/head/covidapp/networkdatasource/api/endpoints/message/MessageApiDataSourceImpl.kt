package com.head.covidapp.networkdatasource.api.endpoints.message

import com.head.covidapp.data.datasources.MessageApiDataSource
import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.arch.callback.callbackApiCall
import com.head.covidapp.domain.arch.callback.mapResponse
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.networkdatasource.api.clients.MainApiClient
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessageMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessagesMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.model.LocationApiModel
import com.head.covidapp.networkdatasource.api.endpoints.message.model.MessageApiModel
import retrofit2.create

class MessageApiDataSourceImpl(
    mainApiClient: MainApiClient,
    private val messagesMapper: MessagesMapper,
    private val messageMapper: MessageMapper
) : MessageApiDataSource {

    private val service = mainApiClient.builder.create<MessageApiService>()

    override suspend fun getMessages(): Response<List<MessageModel>> =
        callbackApiCall {
            service.getMessages()
        }.mapResponse(messagesMapper::apiModelToModel)

    override suspend fun postMessage(
        message: Pair<String, String>,
        userLocation: Pair<Double, Double>
    ): Response<MessageModel> =
        callbackApiCall {
            service.postMessage(
                MessageApiModel(
                    null, message.first, message.second, LocationApiModel(
                        userLocation.first, userLocation.second
                    )
                )
            )
        }.mapResponse(messageMapper::apiModelToModel)
}
