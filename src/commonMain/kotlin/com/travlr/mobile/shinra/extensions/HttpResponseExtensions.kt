package com.travlr.mobile.shinra.extensions

import com.travlr.mobile.shinra.entities.HttpCallResponse
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.toHttpDate
import io.ktor.util.toMap
import io.ktor.utils.io.charsets.Charsets

internal suspend fun HttpResponse.toHttpCallResponse(): HttpCallResponse {

    val statusCode = this.status.toString()
    val responseTime = this.responseTime.toHttpDate()
    val requestTime = this.requestTime.toHttpDate()
    val responseHeaders = this.headers.toMap()
    val responseData = this.readText(Charsets.UTF_8)
    val protocolInfo = mapOf(
        "name" to this.version.name,
        "version" to "${this.version.major}.${this.version.minor}"
    )

    return HttpCallResponse(
        statusCode = statusCode,
        protocolInfo = protocolInfo,
        requestTime = requestTime,
        responseTime = responseTime,
        headers = responseHeaders,
        data = responseData
    )
}
