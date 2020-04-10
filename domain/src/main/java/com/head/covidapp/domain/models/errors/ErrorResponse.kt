package com.head.covidapp.domain.models.errors

data class ErrorResponse(
    val code: Int? = 0,
    val cause: String? = DEFAULT_ERROR
) {

    private companion object {
        const val DEFAULT_ERROR = "Unknown error"
    }
}
