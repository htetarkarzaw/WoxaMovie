package com.htetarkarzaw.data.remote

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    BadRequest(400),
    NotFound(404),
    Conflict(409),
    InternalServerError(500),
    Forbidden(403),
    NotAcceptable(406),
    ServiceUnavailable(503),
    UnAuthorized(401),
}

fun handleException(throwable: Exception): DataResult.CustomMessages {
    return when (throwable) {
        is HttpException -> DataResult.CustomMessages.HttpException
        is TimeoutException -> DataResult.CustomMessages.Timeout
        is UnknownHostException -> DataResult.CustomMessages.ServerError
        is ConnectException -> DataResult.CustomMessages.NoInternet
        is SocketTimeoutException -> DataResult.CustomMessages.SocketTimeOutException
        else -> DataResult.CustomMessages.SomethingWentWrong("Error:${throwable.message ?: throwable.localizedMessage ?: "Something Went Wrong"}")
    }
}


fun handleException(statusCode: Int, message: String): DataResult.CustomMessages {
    return getErrorType(statusCode, message)
}

private fun getErrorType(code: Int, message: String): DataResult.CustomMessages {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> DataResult.CustomMessages.Timeout
        ErrorCodes.UnAuthorized.code -> DataResult.CustomMessages.Unauthorized
        ErrorCodes.InternalServerError.code -> DataResult.CustomMessages.InternalServerError

        ErrorCodes.BadRequest.code -> DataResult.CustomMessages.BadRequest
        ErrorCodes.Conflict.code -> DataResult.CustomMessages.Conflict

        ErrorCodes.NotFound.code -> DataResult.CustomMessages.NotFound
        ErrorCodes.NotAcceptable.code -> DataResult.CustomMessages.NotAcceptable
        ErrorCodes.ServiceUnavailable.code -> DataResult.CustomMessages.ServiceUnavailable
        ErrorCodes.Forbidden.code -> DataResult.CustomMessages.Forbidden
        else -> DataResult.CustomMessages.SomethingWentWrong(message)
    }
}
