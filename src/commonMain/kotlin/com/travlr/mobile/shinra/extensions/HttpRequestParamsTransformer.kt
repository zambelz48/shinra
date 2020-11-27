package com.travlr.mobile.shinra.extensions

import com.travlr.mobile.shinra.utils.jsonSerializer
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import io.ktor.http.content.OutgoingContent

internal fun Set<Map<String, Any>>.toRawJson(): OutgoingContent {

    val params = HashMap<String, Any>()

    forEach {
        it.forEach { entry: Map.Entry<String, Any> ->
            params[entry.key] = when(entry.value) {
                is String -> "${entry.value}"
                is Number, is Collection<*>, is Map<*, *> -> entry.value
                else -> entry.value
            }
        }
    }

    return jsonSerializer.write(params)
}

internal fun Set<Map<String, Any>>.toParameters(): Parameters {

    val params = ParametersBuilder(size)

    forEach {
        it.forEach { entry: Map.Entry<String, Any> ->
            params[entry.key] = "${entry.value}"
        }
    }

    return params.build()
}

internal fun Set<Map<String, Any>>.toFormDataContent(): FormDataContent {
    return FormDataContent(toParameters())
}
