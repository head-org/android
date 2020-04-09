package com.head.covidapp.networkdatasource.api.clients

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApiClient(isLoggingEnable: Boolean, appHost: String) {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (isLoggingEnable) Level.BODY else Level.NONE
                }
            )
            .build()
    }

    val builder : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(appHost)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
