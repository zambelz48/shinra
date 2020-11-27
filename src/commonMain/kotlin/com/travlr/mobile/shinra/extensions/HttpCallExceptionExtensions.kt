package com.travlr.mobile.shinra.extensions

import com.travlr.mobile.shinra.entities.HttpResult
import com.travlr.mobile.shinra.utils.Logger
import io.ktor.client.HttpClient

internal fun Exception.handleHttpCallException(
    client: HttpClient,
    debugMode: Boolean = false
): HttpResult {

    client.close()

    if (debugMode) {
        Logger.printHttpResponseExceptionInfo(this)
        Logger.printHttpClientInfo(client)
    }

    return HttpResult.Failed(this)
}
