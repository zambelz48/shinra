package com.travlr.mobile.shinra.extensions

import com.travlr.mobile.shinra.entities.HttpCallResponse
import com.travlr.mobile.shinra.utils.Logger
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.host
import io.ktor.http.charset
import io.ktor.http.contentType
import kotlinx.coroutines.isActive

internal fun Logger.printHttpRequestInfo(builder: HttpRequestBuilder) {

    print("HTTP REQUEST INFORMATION: \n" +
            "- Protocol: ${builder.url.protocol.name} \n" +
            "- Host: ${builder.host} \n" +
            "- Method: ${builder.method.value} \n" +
            "- ContentType: ${builder.contentType()?.contentType}/${builder.contentType()?.contentSubtype} \n" +
            "- Charset: ${builder.contentType()?.charset()} \n" +
            "- Headers: ${builder.headers.entries()} \n" +
            "- Body: ${builder.body} \n" +
            "- Attributes: ${builder.attributes} \n" +
            "END OF HTTP REQUEST INFORMATION \n\n")
}

internal fun Logger.printHttpResponseInfo(response: HttpCallResponse) {

    print("HTTP RESPONSE INFORMATION: \n" +
            "- Status: ${response.statusCode} \n" +
            "- Response Time: ${response.responseTime} \n" +
            "- Data: ${response.data} \n" +
            "END OF HTTP RESPONSE INFORMATION \n\n")
}

internal fun Logger.printHttpClientInfo(client: HttpClient) {

    print("HTTP CLIENT INFORMATION: \n" +
            "- isActive: ${client.isActive} \n" +
            "END OF HTTP CLIENT INFORMATION \n\n")
}

internal fun Logger.printHttpResponseExceptionInfo(exception: Exception) {

    print("HTTP RESPONSE EXCEPTION: \n" +
            "- Exception: ${exception.message} \n" +
            "END OF HTTP RESPONSE EXCEPTION \n\n")
}
