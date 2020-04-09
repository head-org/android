package com.head.covidapp.domain.models.message

data class MessageModel(
    val id: Int,
    val title: String,
    val content: String,
    val location: LocationModel
)
