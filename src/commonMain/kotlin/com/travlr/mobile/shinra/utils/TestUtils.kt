package com.travlr.mobile.shinra.utils

internal expect fun runBlockingInTest(block: suspend () -> Unit)
