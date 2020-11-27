package com.travlr.mobile.shinra.utils

import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.defaultSerializer

internal actual val jsonSerializer: JsonSerializer = defaultSerializer()
