package com.codingwitharul.bookmyslot.common

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect val client: HttpClient

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient