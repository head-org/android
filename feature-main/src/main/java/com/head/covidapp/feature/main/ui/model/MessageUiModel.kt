package com.head.covidapp.feature.main.ui.model

import com.head.covidapp.domain.models.message.MessageModel
import java.io.Serializable

data class MessageUiModel(
    val messageList: List<MessageModel>
) : Serializable
