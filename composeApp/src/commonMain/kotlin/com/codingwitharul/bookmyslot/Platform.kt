package com.codingwitharul.bookmyslot

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform