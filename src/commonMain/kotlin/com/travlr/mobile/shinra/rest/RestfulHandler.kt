package com.travlr.mobile.shinra.rest

import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.configurations.LIB_TAG
import com.travlr.mobile.shinra.entities.HttpCallRequest
import com.travlr.mobile.shinra.entities.HttpResult
import com.travlr.mobile.shinra.entities.ktorContentType
import com.travlr.mobile.shinra.exceptions.InvalidHttpCallRequestException
import com.travlr.mobile.shinra.extensions.*
import com.travlr.mobile.shinra.utils.Logger
import com.travlr.mobile.shinra.utils.httpCallExecutor
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType

internal class RestfulHandler(
    private val httpConfiguration: HttpConfiguration,
    private val httpCallRequest: HttpCallRequest,
    private val debugMode: Boolean
) {

    suspend fun execute(method: RestfulHttpMethod): HttpResult {

        return httpCallExecutor(httpConfiguration, debugMode) { client: HttpClient ->

            val httpResponse: HttpResponse = when (method) {
                RestfulHttpMethod.GET -> client.get(block = ::configureRequest)
                RestfulHttpMethod.POST -> client.post(block = ::configureRequest)
                RestfulHttpMethod.PUT -> client.put(block = ::configureRequest)
                RestfulHttpMethod.PATCH -> client.patch(block = ::configureRequest)
                RestfulHttpMethod.DELETE -> client.delete(block = ::configureRequest)
                RestfulHttpMethod.HEAD -> client.head(block = ::configureRequest)
                RestfulHttpMethod.OPTIONS -> client.options(block = ::configureRequest)
            }

            val httpCallResponse = httpResponse.toHttpCallResponse()

            client.close()

            if (debugMode) {
                Logger.printHttpResponseInfo(httpCallResponse)
                Logger.printHttpClientInfo(client)
            }

            HttpResult.Success(httpCallResponse)
        }
    }

    private fun configureRequest(builder: HttpRequestBuilder) {

        val urlString: String = this.httpCallRequest.url
            ?: throw InvalidHttpCallRequestException("$LIB_TAG - Error: Url cannot be empty")

        val contentType: ContentType = httpCallRequest.contentType.ktorContentType
        val headers: HashSet<Map<String, String>> = httpCallRequest.requestHeaders
        val parameters: Any? = httpCallRequest.requestParams

        builder.apply {

            url(urlString)

            constructRequestHeaders(headers)
            constructRequestParameters(parameters, contentType)
        }

        if (debugMode) {
            Logger.printHttpRequestInfo(builder)
        }
    }

}
