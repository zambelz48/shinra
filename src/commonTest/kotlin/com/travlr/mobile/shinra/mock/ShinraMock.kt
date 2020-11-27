package com.travlr.mobile.shinra.mock

import com.travlr.mobile.shinra.Shinra
import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.configurations.HttpConfigurationFactory
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.headersOf

class ShinraMock private constructor() {

    private var httpConfiguration: HttpConfiguration = HttpConfigurationFactory.mocked {

        val contentTypes = listOf(ContentType.Text.Plain.toString())
        val responseHeaders = headersOf("Content-Type" to contentTypes)

        respond("Ok", headers = responseHeaders)
    }

    companion object {

        fun shared(): ShinraMock {
            return ShinraMock()
        }

    }

    fun configure(block: suspend MockRequestHandleScope.(request: HttpRequestData) -> HttpResponseData): ShinraMock {
        httpConfiguration = HttpConfigurationFactory.mocked(block)
        return this
    }

    fun build(url: String): Shinra {

        return Shinra.withURL(url)
            .useConfiguration(httpConfiguration)
    }

}
