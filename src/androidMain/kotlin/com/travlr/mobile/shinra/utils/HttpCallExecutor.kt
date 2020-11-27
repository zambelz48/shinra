package com.travlr.mobile.shinra.utils

import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.entities.HttpResult
import com.travlr.mobile.shinra.exceptions.InvalidHttpCallRequestException
import com.travlr.mobile.shinra.extensions.handleHttpCallException
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.ServerResponseException

internal actual suspend fun httpCallExecutor(
    httpConfiguration: HttpConfiguration,
    debugMode: Boolean,
    onAction: suspend (client: HttpClient) -> HttpResult
): HttpResult {

    val client = httpConfiguration.httpClient()

    return try {
        onAction(client)
    } catch (exception: RedirectResponseException) {
        exception.handleHttpCallException(client, debugMode)
    } catch (exception: ClientRequestException) {
        exception.handleHttpCallException(client, debugMode)
    } catch (exception: ServerResponseException) {
        exception.handleHttpCallException(client, debugMode)
    } catch (exception: ResponseException) {
        exception.handleHttpCallException(client, debugMode)
    } catch (exception: InvalidHttpCallRequestException) {
        exception.handleHttpCallException(client, debugMode)
    }
}
