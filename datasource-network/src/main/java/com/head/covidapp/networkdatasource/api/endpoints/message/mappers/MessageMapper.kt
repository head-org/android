package com.head.covidapp.networkdatasource.api.endpoints.message.mappers

import com.head.covidapp.domain.arch.mappers.DoubleMapper
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.networkdatasource.api.endpoints.message.model.MessageApiModel

class MessageMapper(private val locationMapper: LocationMapper) :
    DoubleMapper<MessageApiModel?, MessageModel> {

    override fun modelToApiModel(model: MessageModel): MessageApiModel? =
        MessageApiModel(
            model.id,
            model.title,
            model.content,
            locationMapper.modelToApiModel(model.location)
        )

    override fun apiModelToModel(apiModel: MessageApiModel?): MessageModel =
        MessageModel(
            apiModel?.id ?: 0,
            apiModel?.title ?: "",
            apiModel?.content ?: "",
            locationMapper.apiModelToModel(apiModel?.location)
        )
}
