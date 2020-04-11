package com.head.covidapp.networkdatasource.api.endpoints.message

import com.head.covidapp.networkdatasource.api.endpoints.message.model.MessageApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MessageApiService {

    @GET("api/posts")
    suspend fun getMessages(): List<MessageApiModel>

    @POST("api/posts")
    suspend fun postMessage(@Body messageApiModel: MessageApiModel): MessageApiModel
}
