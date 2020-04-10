package com.head.covidapp.networkdatasource.api.endpoints.message

import com.head.covidapp.networkdatasource.api.endpoints.message.model.MessageApiModel
import retrofit2.http.GET

interface MessageApiService {

    @GET("api/posts")
    suspend fun getMessages(): List<MessageApiModel>
}
