package com.travlr.mobile.shinra.configurations

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import kotlinx.coroutines.CoroutineDispatcher

// TODO: Add documentation later
interface HttpConfiguration {

    // TODO: Add documentation later
    fun httpClient(): HttpClient

    // TODO: Add documentation later
    fun httpCallDispatcher(): CoroutineDispatcher

}

// TODO: Add documentation later
expect object HttpConfigurationFactory {

    // TODO: Add documentation later
    fun default(): HttpConfiguration

    // TODO: Add documentation later
    fun mocked(requestHandler: suspend MockRequestHandleScope.(request: HttpRequestData) -> HttpResponseData): HttpConfiguration

}
