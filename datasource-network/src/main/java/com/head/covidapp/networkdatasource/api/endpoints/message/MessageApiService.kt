package com.head.covidapp.networkdatasource.api.endpoints.message

import com.head.covidapp.networkdatasource.api.endpoints.message.model.MessageApiModel
import retrofit2.http.GET

interface MessageApiService {

    @GET("api/Posts")
    suspend fun getMessages(): List<MessageApiModel>
}
