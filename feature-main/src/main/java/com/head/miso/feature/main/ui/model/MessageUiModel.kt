package com.head.miso.feature.main.ui.model

import com.head.miso.domain.models.message.MessageModel
import java.io.Serializable

data class MessageUiModel(
    val messageList: List<MessageModel>
) : Serializable
