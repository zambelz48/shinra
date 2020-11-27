package com.travlr.mobile.shinra.entities

import io.ktor.http.ContentType

enum class HttpContentType {
    JSON,
    FORM_URL_ENCODED
}

internal val HttpContentType.ktorContentType: ContentType get() {
    return when (this) {
        HttpContentType.JSON -> ContentType.Application.Json
        HttpContentType.FORM_URL_ENCODED -> ContentType.Application.FormUrlEncoded
    }
}
