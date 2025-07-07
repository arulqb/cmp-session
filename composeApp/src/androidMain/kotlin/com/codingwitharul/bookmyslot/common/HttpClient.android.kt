package com.codingwitharul.bookmyslot.common

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
    engine {
        config {
            retryOnConnectionFailure(false)
            connectTimeout(30, TimeUnit.SECONDS)
        }
    }
//    platform oriented config goes here
}