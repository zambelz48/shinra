package com.travlr.mobile.shinra

import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.configurations.HttpConfigurationFactory
import com.travlr.mobile.shinra.configurations.UNKNOWN_ERROR_MESSAGE
import com.travlr.mobile.shinra.entities.HttpCallRequest
import com.travlr.mobile.shinra.entities.HttpCallResponse
import com.travlr.mobile.shinra.entities.HttpResult
import com.travlr.mobile.shinra.exceptions.InvalidHttpCallRequestException
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class Shinra internal constructor(
    url: String
) {
    private var httpCallRequest: HttpCallRequest? = null
    private var httpConfiguration: HttpConfiguration = HttpConfigurationFactory.default()
    private var debugMode: Boolean = false

    init {
        httpCallRequest = HttpCallRequest(url = url)
    }

    companion object {

        // TODO: Add documentation later
        fun withURL(url: String): Shinra {
            return Shinra(url)
        }

    }

    fun useConfiguration(httpConfiguration: HttpConfiguration): Shinra {
        this.httpConfiguration = httpConfiguration
        return this
    }

    fun debug(debugMode: Boolean): Shinra {
        this.debugMode = debugMode
        return this
    }

    // TODO: Add documentation later
    open fun additionalHeader(key: String, value: String): Shinra {

        val request = httpCallRequest ?: return this

        if (key.isEmpty() || value.isEmpty() || key == HttpHeaders.ContentType) {
            return this
        }

        request.requestHeaders.add(mapOf(key to value))

        return this
    }

    // TODO: Add documentation later
    open fun additionalHeaders(headers: Map<String, String>): Shinra {

        if (headers.isNullOrEmpty()) {
            return this
        }

        headers.forEach { header: Map.Entry<String, String> ->
            additionalHeader(header.key, header.value)
        }

        return this
    }

    // TODO: Add documentation later
    open fun addParameter(key: String, value: Any): Shinra {

        val request = httpCallRequest ?: return this

        if (request.requestParams != null && request.requestParams !is Set<*>) {
            return this
        }

        if (request.requestParams == null) {
            request.requestParams = HashSet<Map<String, Any>>()
        }

        if (key.isNotBlank()) {
            @Suppress("UNCHECKED_CAST")
            val params = request.requestParams as? HashSet<Map<String, Any>> ?: return this
            params.add(mapOf(key to value))

            return this
        }

        return this
    }

    // TODO: Add documentation later
    open fun addParameters(params: Map<String, Any>): Shinra {

        if (params.isEmpty()) {
            return this
        }

        params.forEach { param: Map.Entry<String, Any> ->
            addParameter(param.key, param.value)
        }

        return this
    }

    // TODO: Add documentation later
    open fun <T> addParameter(params: T): Shinra {

        val request = httpCallRequest ?: return this
        request.requestParams = params

        return this
    }

    // TODO: Add documentation later
    internal suspend fun request(
        configure: suspend (HttpConfiguration, HttpCallRequest, Boolean) -> HttpResult
    ): HttpResult = coroutineScope {

        val context = httpConfiguration.httpCallDispatcher()

        withContext(context) {

            val request = httpCallRequest ?: throw InvalidHttpCallRequestException()
            configure(httpConfiguration, request, debugMode)

        }
    }

    // TODO: Add documentation later
    internal fun request(
        configure: suspend (HttpConfiguration, HttpCallRequest, Boolean) -> HttpResult,
        onSuccess: (response: HttpCallResponse) -> Unit,
        onError: ((error: Throwable) -> Unit)? = null
    ) {

        val context = httpConfiguration.httpCallDispatcher()

        GlobalScope.launch(context) {

            val request = httpCallRequest ?: throw InvalidHttpCallRequestException()
            val result = configure(httpConfiguration, request, debugMode)
            val response = result.response()

            if (result.isSuccess() && response != null) {
                onSuccess(response)
            } else {
                val error = result.error() ?: Throwable(UNKNOWN_ERROR_MESSAGE)
                val errorCallback = onError ?: {}
                errorCallback(error)
            }
        }
    }

}
