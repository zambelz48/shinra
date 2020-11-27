package com.travlr.mobile.shinra

import com.travlr.mobile.shinra.entities.HttpCallResponse
import com.travlr.mobile.shinra.extensions.fullUrl
import com.travlr.mobile.shinra.mock.ShinraMock
import com.travlr.mobile.shinra.rest.*
import com.travlr.mobile.shinra.utils.runBlockingInTest
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.headersOf
import kotlin.test.*

class BasicHttpRequestTests {

    private val fakeHost = "https://fake-domain.com"
    private val fakeEndpoints = setOf(
        "/fake-get",
        "/fake-post",
        "/fake-put",
        "/fake-patch",
        "/fake-delete",
        "/fake-head",
        "/fake-options"
    )
    private val fakeResponse = "Response success"

    private var shinraMock: ShinraMock? = null

    @BeforeTest
    fun setUp() {
        // Set up any required value or logic here

        shinraMock = ShinraMock.shared().configure { request ->

            val url = request.url

            assertEquals(url.fullUrl, fakeHost, "Invalid host: ${url.fullUrl}")
            assertTrue(fakeEndpoints.contains(url.encodedPath), "Endpoint not found: ${url.encodedPath}")

            when (url.fullUrl) {
                fakeHost -> {
                    val contentTypes = listOf(ContentType.Text.Plain.toString())
                    val responseHeaders = headersOf("Content-Type" to contentTypes)

                    respond(fakeResponse, headers = responseHeaders)
                }
                else -> {
                    error("Invalid host: ${url.host}")
                }
            }
        }
    }

    @AfterTest
    fun tearDown() {
        // Reset any value or logic which configured in `setUp` method
        shinraMock = null
    }

    @Test
    fun `test for http 'GET' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-get")
                .get({ response: HttpCallResponse ->

                    println("RESPONSE [GET]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'POST' method`() {

        val shinraMock = this.shinraMock ?: return
        val fakeHeaders = mapOf(
            "Authorization" to "!*&#&#DBHShbdfhfds&#$7835"
        )
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-post")
                .additionalHeaders(fakeHeaders)
                .post({ response: HttpCallResponse ->

                    println("RESPONSE [POST]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: "Failed to retrieve data"
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'PUT' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-put")
                .put({ response: HttpCallResponse ->

                    println("RESPONSE [PUT]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'PATCH' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-patch")
                .patch({ response: HttpCallResponse ->

                    println("RESPONSE [PATCH]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'DELETE' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-delete")
                .delete({ response: HttpCallResponse ->

                    println("RESPONSE [DELETE]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'HEAD' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-head")
                .head({ response: HttpCallResponse ->

                    println("RESPONSE [HEAD]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

    @Test
    fun `test for http 'OPTIONS' method`() {

        val shinraMock = this.shinraMock ?: return
        var actualResponse = ""

        runBlockingInTest {

            shinraMock.build("${fakeHost}/fake-options")
                .options({ response: HttpCallResponse ->

                    println("RESPONSE [OPTIONS]")
                    println("statusCode : ${response.statusCode}")
                    println("headers : ${response.headers}")
                    println("protocolInfo : ${response.protocolInfo}")
                    println("requestTime : ${response.requestTime}")
                    println("data : ${response.data}")

                    actualResponse = response.data ?: ""
                })

        }

        assertEquals(fakeResponse, actualResponse)
    }

}
