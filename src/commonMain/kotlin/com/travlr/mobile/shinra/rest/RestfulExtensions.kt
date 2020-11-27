package com.travlr.mobile.shinra.rest

import com.travlr.mobile.shinra.Shinra
import com.travlr.mobile.shinra.configurations.HttpConfiguration
import com.travlr.mobile.shinra.entities.HttpCallRequest
import com.travlr.mobile.shinra.entities.HttpCallResponse
import com.travlr.mobile.shinra.entities.HttpContentType
import com.travlr.mobile.shinra.entities.HttpResult
import com.travlr.mobile.shinra.utils.Promise

//region Private methods
private suspend fun createRestfulRequest(
    config: HttpConfiguration,
    method: RestfulHttpMethod,
    request: HttpCallRequest,
    contentType: HttpContentType? = null,
    debugMode: Boolean = false
): HttpResult {

    if (contentType != null) {
        request.contentType = contentType
    }

    val rest = RestfulHandler(config, request, debugMode)
    return rest.execute(method)
}

private suspend fun Shinra.executeRestfulRequest(
    method: RestfulHttpMethod,
    contentType: HttpContentType? = null
): HttpResult {
    return request { config, request, debugMode ->
        createRestfulRequest(config, method, request, contentType, debugMode)
    }
}

private fun Shinra.executeRestfulRequest(
    method: RestfulHttpMethod,
    contentType: HttpContentType? = null,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) {
    request(
        configure = { config, request, debugMode ->
            createRestfulRequest(config, method, request, contentType, debugMode)
        },
        onSuccess = onSuccess,
        onError = onError
    )
}
//endregion

//region method: GET
// TODO: Add documentation later
suspend fun Shinra.get(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.GET, contentType)

// TODO: Add documentation later
suspend fun Shinra.get(): HttpResult = get(null)

// TODO: Add documentation later
fun Shinra.get(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.GET, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.get(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = get(null, onSuccess, onError)
//endregion

//region method: POST
// TODO: Add documentation later
suspend fun Shinra.post(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.POST, contentType)

// TODO: Add documentation later
suspend fun Shinra.post(): HttpResult = post(null)

// TODO: Add documentation later
fun Shinra.post(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.POST, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.post(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = post(null, onSuccess, onError)
//endregion

//region method: PUT
// TODO: Add documentation later
suspend fun Shinra.put(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.PUT, contentType)

// TODO: Add documentation later
suspend fun Shinra.put(): HttpResult = put(null)

// TODO: Add documentation later
fun Shinra.put(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.PUT, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.put(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = put(null, onSuccess, onError)
//endregion

//region method: PATCH
// TODO: Add documentation later
suspend fun Shinra.patch(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.PATCH, contentType)

// TODO: Add documentation later
suspend fun Shinra.patch(): HttpResult = patch(null)

// TODO: Add documentation later
fun Shinra.patch(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.PATCH, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.patch(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = patch(null, onSuccess, onError)
//endregion

//region method: DELETE
// TODO: Add documentation later
suspend fun Shinra.delete(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.DELETE, contentType)

// TODO: Add documentation later
suspend fun Shinra.delete(): HttpResult = delete(null)

// TODO: Add documentation later
fun Shinra.delete(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.DELETE, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.delete(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = delete(null, onSuccess, onError)
//endregion

//region method: HEAD
// TODO: Add documentation later
suspend fun Shinra.head(contentType: HttpContentType?): HttpResult = executeRestfulRequest(RestfulHttpMethod.HEAD, contentType)

// TODO: Add documentation later
suspend fun Shinra.head(): HttpResult = head(null)

// TODO: Add documentation later
fun Shinra.head(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.HEAD, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.head(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = head(null, onSuccess, onError)
//endregion

//region method: OPTIONS
// TODO: Add documentation later
suspend fun Shinra.options(contentType: HttpContentType? = null): HttpResult = executeRestfulRequest(RestfulHttpMethod.OPTIONS, contentType)

// TODO: Add documentation later
suspend fun Shinra.options(): HttpResult = options(null)

// TODO: Add documentation later
fun Shinra.options(
    contentType: HttpContentType?,
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = executeRestfulRequest(RestfulHttpMethod.OPTIONS, contentType, onSuccess, onError)

// TODO: Add documentation later
fun Shinra.options(
    onSuccess: (response: HttpCallResponse) -> Unit,
    onError: ((error: Throwable) -> Unit)? = null
) = options(null, onSuccess, onError)
//endregion

//region Promise

// TODO: Add documentation later
fun Shinra.rest(
    method: RestfulHttpMethod,
    contentType: HttpContentType? = null
): Promise<HttpCallResponse> = RestfulPromise(this, method, contentType)

//endregion
