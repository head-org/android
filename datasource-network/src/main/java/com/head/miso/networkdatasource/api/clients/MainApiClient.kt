package com.head.miso.networkdatasource.api.clients

import com.head.miso.networkdatasource.api.clients.interceptors.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApiClient(
    isLoggingEnable: Boolean,
    appHost: String,
    private val token: String
) {

    private val interceptors: List<Interceptor>
        get() = listOf(HeaderInterceptor(token))

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            interceptors.forEach { addInterceptor(it) }
        }.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (isLoggingEnable) Level.BODY else Level.NONE
            }
        ).build()
    }

    val builder: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(appHost)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
