package com.head.miso.networkdatasource.api.endpoints.message

import com.head.miso.data.datasources.MessageApiDataSource
import com.head.miso.domain.arch.callback.Response
import com.head.miso.domain.arch.callback.callbackApiCall
import com.head.miso.domain.arch.callback.mapResponse
import com.head.miso.domain.models.message.MessageModel
import com.head.miso.networkdatasource.api.clients.MainApiClient
import com.head.miso.networkdatasource.api.endpoints.message.mappers.MessageMapper
import com.head.miso.networkdatasource.api.endpoints.message.mappers.MessagesMapper
import com.head.miso.networkdatasource.api.endpoints.message.model.LocationApiModel
import com.head.miso.networkdatasource.api.endpoints.message.model.MessageApiModel
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
