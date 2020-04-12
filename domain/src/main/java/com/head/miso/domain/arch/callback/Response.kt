package com.head.miso.domain.arch.callback

import com.head.miso.domain.arch.callback.Response.Failure
import com.head.miso.domain.arch.callback.Response.Success
import com.head.miso.domain.models.errors.ErrorResponse

sealed class Response<out T> {
    data class Success<out T>(val value: T) : Response<T>()
    data class Failure(val error: ErrorResponse? = null) : Response<Nothing>()
}

inline fun <T, V> Response<T>.mapResponse(lambda: (T) -> V): Response<V> =
    when (this) {
        is Success -> Success(lambda(value))
        is Failure -> Failure(error)
    }

inline fun <T, V> Response<T>.onCallback(onSuccess: (T) -> V, onFailure: (ErrorResponse?) -> V): V =
    when (this) {
        is Success -> onSuccess.invoke(value)
        is Failure -> onFailure.invoke(error)
    }
