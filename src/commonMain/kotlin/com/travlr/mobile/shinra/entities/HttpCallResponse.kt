package com.travlr.mobile.shinra.entities

// TODO: Add documentation later
data class HttpCallResponse (
    val statusCode: String,
    val requestTime: String,
    val responseTime: String,
    val headers: Map<String, List<String>>,
    val protocolInfo: Map<String, String>,
    val data: String?
)
