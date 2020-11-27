package com.travlr.mobile.shinra.configurations

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.features.addDefaultResponseValidation
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object HttpConfigurationFactory {

    actual fun default(): HttpConfiguration {
        return object : HttpConfiguration {

            override fun httpClient(): HttpClient = HttpClient(Android) {
                install(JsonFeature) {
                    serializer = defaultSerializer()
                }
                addDefaultResponseValidation()
            }

            override fun httpCallDispatcher(): CoroutineDispatcher = Dispatchers.Main

        }
    }

    actual fun mocked(requestHandler: suspend MockRequestHandleScope.(request: HttpRequestData) -> HttpResponseData): HttpConfiguration {
        return object : HttpConfiguration {

            override fun httpClient(): HttpClient = HttpClient(MockEngine) {
                engine {
                    addHandler(requestHandler)
                }
            }

            override fun httpCallDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined

        }
    }

}
