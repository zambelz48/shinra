package com.travlr.mobile.shinra.utils

interface Promise<out T> {

    fun resolve(block: (T) -> Unit): Promise<T>

    fun catch(block: (Throwable) -> Unit)

}
