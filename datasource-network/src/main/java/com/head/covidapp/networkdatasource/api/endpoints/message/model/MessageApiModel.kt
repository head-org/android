package com.head.covidapp.networkdatasource.api.endpoints.message.model

import com.google.gson.annotations.SerializedName

data class MessageApiModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("location")
    val location: LocationApiModel?
)
