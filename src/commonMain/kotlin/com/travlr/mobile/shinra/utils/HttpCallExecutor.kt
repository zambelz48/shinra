package com.travlr.mobile.shinra.utils

import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.entities.HttpResult
import io.ktor.client.HttpClient

internal expect suspend fun httpCallExecutor(
    httpConfiguration: HttpConfiguration,
    debugMode: Boolean,
    onAction: suspend (client: HttpClient) -> HttpResult
): HttpResult
