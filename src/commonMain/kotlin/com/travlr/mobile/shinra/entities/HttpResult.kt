package com.travlr.mobile.shinra.entities

// TODO: Add documentation later
sealed class HttpResult {

    data class Success(
        val response: HttpCallResponse
    ) : HttpResult()

    data class Failed(
        val error: Throwable
    ) : HttpResult()

    fun isSuccess(): Boolean = this is Success

    fun isFailed(): Boolean = this is Failed

    fun response(): HttpCallResponse? = when (this) {
        is Success -> response
        else -> null
    }

    fun error(): Throwable? = when (this) {
        is Failed -> error
        else -> null
    }

    operator fun invoke(onSuccess: (HttpCallResponse) -> Any, onFailed: (Throwable) -> Any): Any =
        when (this) {
            is Success -> onSuccess(response)
            is Failed -> onFailed(error)
        }
}
