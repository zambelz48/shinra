package com.travlr.mobile.shinra.entities

internal data class HttpCallRequest (
    val url: String? = null,
    val requestHeaders: HashSet<Map<String, String>> = HashSet(),
    var contentType: HttpContentType = HttpContentType.JSON,
    var requestParams: Any? = null
)
