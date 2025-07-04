package com.codingwitharul.bookmyslot.data.networking

import com.codingwitharul.bookmyslot.common.httpClient
import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.data.networking.models.ApiResponse
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class ApiClientHelper(val db: DatabaseHelper) {


    var client: HttpClient

    init {
        client = createClient(null)
    }

    fun authClient(userInfo: UserInfo) {
        client = createClient(userInfo)
    }

    fun createClient(userInfo: UserInfo?): HttpClient {

        return httpClient {
            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "ApiCall", message = message)
                    }
                }
                level = LogLevel.ALL
            }

            install(Auth) {
                userInfo?.let {
                    bearer {
                        setupAuth(userInfo, db)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
        }
    }


    fun BearerAuthConfig.setupAuth(
        userInfo: UserInfo,
        storageManger: DatabaseHelper
    ) {
        sendWithoutRequest { hb ->
            Napier.d(tag = "ApiHelper", message = "sendWithoutRequest")
            if (hb.isLoginUrl()) {
                return@sendWithoutRequest false
            }
            userInfo.authToken?.let {
                hb.bearerAuth(it)
                Napier.d(
                    tag = "ApiHelper",
                    message = "Authorization: ${hb.headers[HttpHeaders.Authorization]}"
                )
            }
            true
        }
        loadTokens {
            Napier.d(tag = "ApiHelper", message = "loadTokens userInfo=$userInfo")
            userInfo.authToken?.let {
                BearerTokens(it, "")
            }
        }
//        refreshTokens {
//        }
    }

    private fun HttpRequestBuilder.isLoginUrl(): Boolean {
        return this.url.toString().contains(EndPoints.Auth)
    }
}

fun HttpRequestBuilder.apiEndPoint(endPoint: String) {
    headers {
        set("Content-Type", "application/json;charset=utf-8")
    }
    url(Constants.HOST + endPoint)
}



suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> {
    return try {
//        if (Utils.hasInternetConnection?.invoke() == false) {
//            Napier.e(tag = "ApiCall", message = "No internet connection.")
//            return ApiResponse.Error.NetworkError
//        }
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        if (e.response.status == HttpStatusCode.Unauthorized)
            ApiResponse.Error.Unauthorized(e.response.status.value, e.errorBody())
        else ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        ApiResponse.Error.NetworkError
    } catch (e: SerializationException) {
        ApiResponse.Error.SerializationError
    } catch (e: Exception) {
        Napier.e(tag = "ApiCall", message = e.message ?: "")
        ApiResponse.Error.SerializationError
    }
}

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: Exception) {
        null
    }

