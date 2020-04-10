package com.head.covidapp.domain.models.message

import java.io.Serializable

data class LocationModel(
    val latitude: Double,
    val longitude: Double
) : Serializable
