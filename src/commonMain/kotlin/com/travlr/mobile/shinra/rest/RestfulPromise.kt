package com.travlr.mobile.shinra.rest

import com.travlr.mobile.shinra.Shinra
import com.travlr.mobile.shinra.entities.HttpCallResponse
import com.travlr.mobile.shinra.entities.HttpContentType
import com.travlr.mobile.shinra.utils.Promise

internal class RestfulPromise (
    shinra: Shinra,
    method: RestfulHttpMethod,
    contentType: HttpContentType? = null
) : Promise<HttpCallResponse> {

    private var successCallback: ((HttpCallResponse) -> Unit)? = null
    private var errorCallback: ((Throwable) -> Unit)? = null

    init {
        shinra.request(
            configure = { config, request, debugMode ->

                if (contentType != null) {
                    request.contentType = contentType
                }

                val rest = RestfulHandler(config, request, debugMode)
                rest.execute(method)
            },
            onSuccess = {
                successCallback?.invoke(it)
            },
            onError = {
                errorCallback?.invoke(it)
            }
        )
    }

    override fun resolve(block: (HttpCallResponse) -> Unit): RestfulPromise {

        successCallback = { result: HttpCallResponse ->
            block(result)
        }

        return this
    }

    override fun catch(block: (Throwable) -> Unit) {

        errorCallback = { error: Throwable ->
            block(error)
        }
    }

}
