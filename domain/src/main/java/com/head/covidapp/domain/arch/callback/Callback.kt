package com.head.covidapp.domain.arch.callback

import com.google.gson.Gson
import com.head.covidapp.domain.models.errors.ErrorResponse
import retrofit2.HttpException

suspend fun <T> callbackApiCall(apiCall: suspend () -> T) =
    try {
        Response.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                Response.Failure(convertErrorBody(throwable))
            }
            else -> Response.Failure(null)
        }
    }

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        Gson().fromJson(throwable.response()?.errorBody()?.charStream(), ErrorResponse::class.java)
    } catch (exception: Exception) {
        null
    }
}
