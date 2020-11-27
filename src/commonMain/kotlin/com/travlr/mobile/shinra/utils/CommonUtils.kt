package com.travlr.mobile.shinra.utils

import com.travlr.mobile.shinra.configurations.LIB_TAG

internal object Logger {

    fun print(message: String?) {

        if (!message.isNullOrBlank()) {
            println("$LIB_TAG - $message")
        }
    }
}
