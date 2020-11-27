package com.travlr.mobile.shinra.extensions

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal fun HttpRequestBuilder.constructRequestHeaders(headers: Set<Map<String, String>>) {

    if (headers.isEmpty()) {
        return
    }

    apply {
        headers {
            headers.forEach {
                it.forEach { entry: Map.Entry<String, String> ->
                    append(entry.key, entry.value)
                }
            }
        }
    }
}

internal fun HttpRequestBuilder.constructRequestParameters(params: Any?, contentType: ContentType) {

    if (params == null) {
        return
    }

    apply {

        if (contentType != ContentType.Application.FormUrlEncoded) {
            contentType(contentType)
        }

        body = when (contentType) {

            ContentType.Application.Json -> {
                when (params) {
                    is HashSet<*> -> createHashSet(params).toRawJson()
                    else -> params
                }
            }

            ContentType.Application.FormUrlEncoded -> createHashSet(params).toFormDataContent()

            else -> createHashSet(params).toRawJson()
        }
    }
}

private fun createHashSet(params: Any?): Set<Map<String, Any>> {

    @Suppress("UNCHECKED_CAST")

    return params as? HashSet<HashMap<String, Any>>
        ?: emptySet()
}
