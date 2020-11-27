package com.travlr.mobile.shinra.utils

import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer

internal actual val jsonSerializer: JsonSerializer = KotlinxSerializer()
