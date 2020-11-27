package com.travlr.mobile.shinra.configurations

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.features.addDefaultResponseValidation
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext

actual object HttpConfigurationFactory {

    actual fun default(): HttpConfiguration {
        return object : HttpConfiguration {

            override fun httpClient(): HttpClient = HttpClient(Ios) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                addDefaultResponseValidation()
            }

            override fun httpCallDispatcher(): CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

        }
    }

    actual fun mocked(requestHandler: suspend MockRequestHandleScope.(request: HttpRequestData) -> HttpResponseData): HttpConfiguration {
        return object : HttpConfiguration {

            override fun httpClient(): HttpClient = HttpClient(MockEngine) {
                engine {
                    addHandler(requestHandler)
                }
            }

            override fun httpCallDispatcher(): CoroutineDispatcher = NsQueueDispatcher()

        }
    }

    private class NsQueueDispatcher(
        private val dispatchQueue: dispatch_queue_t = null
    ) : CoroutineDispatcher() {

        override fun dispatch(context: CoroutineContext, block: Runnable) {

            if (dispatchQueue == null) {
                block.run()
                return
            }

            dispatch_async(dispatchQueue) {
                block.run()
            }
        }

    }

}
