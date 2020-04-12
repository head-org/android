package com.head.miso.feature.main.ui.mapper

import com.head.miso.domain.models.message.MessageModel
import com.head.miso.feature.main.ui.model.MessageUiModel

class MapperMessageUiModel {

    fun map(messageList: List<MessageModel>?): MessageUiModel =
        MessageUiModel(
            messageList ?: emptyList()
        )
}
