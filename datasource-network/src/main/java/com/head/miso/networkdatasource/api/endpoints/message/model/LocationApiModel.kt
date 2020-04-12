package com.head.miso.networkdatasource.api.endpoints.message.model

import com.google.gson.annotations.SerializedName

data class LocationApiModel(
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("long")
    val longitude: Double?
)
