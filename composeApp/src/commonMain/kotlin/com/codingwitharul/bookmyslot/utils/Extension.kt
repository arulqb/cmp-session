package com.codingwitharul.bookmyslot.utils

fun String.toThrowable(): Throwable {
    return Exception(this)
}

