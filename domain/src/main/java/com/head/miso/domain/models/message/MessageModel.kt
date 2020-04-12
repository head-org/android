package com.head.miso.domain.models.message

import java.io.Serializable

data class MessageModel(
    val id: Int,
    val title: String,
    val content: String,
    val location: LocationModel
) : Serializable
