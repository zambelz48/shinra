package com.travlr.mobile.shinra.exceptions

internal data class InvalidHttpCallRequestException (
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(
    message ?: "Invalid 'HttpCallRequest' parameter value",
    cause
)
