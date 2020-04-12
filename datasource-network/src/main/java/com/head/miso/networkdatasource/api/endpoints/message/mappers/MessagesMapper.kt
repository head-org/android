package com.head.miso.networkdatasource.api.endpoints.message.mappers

import com.head.miso.domain.arch.mappers.DoubleMapper
import com.head.miso.domain.models.message.MessageModel
import com.head.miso.networkdatasource.api.endpoints.message.model.MessageApiModel

class MessagesMapper(private val locationMapper: LocationMapper) :
    DoubleMapper<List<MessageApiModel?>?, List<MessageModel>?> {

    override fun modelToApiModel(model: List<MessageModel>?): List<MessageApiModel?>? =
        model?.map {
            MessageApiModel(
                it.id,
                it.title,
                it.content,
                locationMapper.modelToApiModel(it.location)
            )
        } ?: emptyList()

    override fun apiModelToModel(apiModel: List<MessageApiModel?>?): List<MessageModel> =
        apiModel?.filterNotNull()?.map {
            MessageModel(
                it.id ?: 0,
                it.title ?: "",
                it.content ?: "",
                locationMapper.apiModelToModel(it.location)
            )
        } ?: emptyList()
}
