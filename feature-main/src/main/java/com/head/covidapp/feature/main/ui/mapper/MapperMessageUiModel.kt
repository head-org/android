package com.head.covidapp.feature.main.ui.mapper

import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.feature.main.ui.model.MessageUiModel

class MapperMessageUiModel {

    fun map(messageList: List<MessageModel>?): MessageUiModel =
        MessageUiModel(
            messageList ?: emptyList()
        )
}
