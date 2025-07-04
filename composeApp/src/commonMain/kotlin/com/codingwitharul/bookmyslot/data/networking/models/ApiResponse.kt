package com.codingwitharul.bookmyslot.data.networking.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class ApiResponse<out T, out E> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<T>(val body: T) : ApiResponse<T, Nothing>()

    sealed class Error<E> : ApiResponse<Nothing, E>() {
        /**
         * Represents server (50x) and client (40x) errors.
         */
        data class HttpError<E>(val code: Int?, val errorBody: E?) : Error<E>()

        /**
         * Represents request data error
         */
        data class InvalidRequest(val message: String?) : Error<Nothing>()

        data class Unauthorized<E>(val code: Int?, val errorBody: E?) : Error<E>()

        /**
         * Represent IOExceptions and connectivity issues.
         */
        data object NetworkError : Error<Nothing>()

        /**
         * Represent SerializationExceptions.
         */
        data object SerializationError : Error<Nothing>()
    }
}

@Serializable
data class ApiError(
    @SerialName("message") val message: String? = "",
    @SerialName("statusCode") val status: Int? = -1,
)

enum class ApiStatus {
    SUCCESS,
    FAILED
}

enum class ErrorType {
    LOGIN,
    HOME
}

data class ErrorData(val message: String, val type: ErrorType, val code: Int = 0)

internal fun <T> ApiResponse.Error<T>.getErrorData(type: ErrorType): ErrorData {
    val defaultErr = "Something went wrong."
    return when (this) {
        is ApiResponse.Error.HttpError -> {
            ErrorData(
                (this.errorBody as? ApiError)?.message ?: this.code?.let { "Error: $it" }
                ?: defaultErr,
                type
            )
        }

        is ApiResponse.Error.Unauthorized -> {
            ErrorData(
                (this.errorBody as? ApiError)?.message ?: "Unauthorized error",
                type,
                code = code ?: 0
            )
        }

        is ApiResponse.Error.NetworkError -> {
            ErrorData("No internet connection.", type)
        }

        else -> {
            ErrorData("Something went wrong.", type)
        }
    }
}