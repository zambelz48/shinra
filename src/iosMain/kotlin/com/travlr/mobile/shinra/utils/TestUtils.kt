package com.travlr.mobile.shinra.utils

import kotlinx.coroutines.runBlocking

internal actual fun runBlockingInTest(block: suspend () -> Unit) = runBlocking {
    block()
}
