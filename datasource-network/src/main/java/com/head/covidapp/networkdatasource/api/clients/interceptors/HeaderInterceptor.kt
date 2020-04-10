package com.head.covidapp.networkdatasource.api.clients.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(HEADER_NAME, HEADER_VALUE + token)
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val HEADER_NAME = "Authorization"
        const val HEADER_VALUE = "Bearer "

    }
}
