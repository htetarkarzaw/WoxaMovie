package com.htetarkarzaw.data.remote

import retrofit2.Response

sealed class DataResult<out T>(
    open val data: T? = null,
    private val message: CustomMessages = CustomMessages.SomethingWentWrong("Something Went Wrong")
) {
    class SuccessEvent<out T>(override val data: T) : DataResult<T>(data)
    class ErrorEvent<T>(errorMessage: CustomMessages) : DataResult<T>(null, errorMessage)

    sealed class CustomMessages(val message: String = "") {
        data object Timeout : CustomMessages()
        data object EmptyData : CustomMessages()
        data object ServerError : CustomMessages()
        data object HttpException : CustomMessages()
        data object SocketTimeOutException : CustomMessages()
        data object NoInternet : CustomMessages()
        data object Unauthorized : CustomMessages()
        data object InternalServerError : CustomMessages()
        data object BadRequest : CustomMessages()
        data object Conflict : CustomMessages()
        data object NotFound : CustomMessages()
        data object NotAcceptable : CustomMessages()
        data object ServiceUnavailable : CustomMessages()
        data object Forbidden : CustomMessages()
        data class SomethingWentWrong(val error: String) : CustomMessages(message = error)
    }

    fun getErrorMessage(): String {
        return when (message) {
            is CustomMessages.BadRequest -> message.message
            CustomMessages.Conflict -> "Conflict"
            CustomMessages.EmptyData -> "Empty Data"
            CustomMessages.Forbidden -> "Forbidden"
            CustomMessages.HttpException -> "HttpException"
            CustomMessages.InternalServerError -> "Internal Server Error"
            CustomMessages.NoInternet -> "No Internet"
            CustomMessages.NotAcceptable -> "NotAcceptable"
            CustomMessages.NotFound -> message.message
            CustomMessages.ServerError -> "Server Error"
            CustomMessages.ServiceUnavailable -> "Service Unavailable"
            CustomMessages.SocketTimeOutException -> "SocketTimeOutException"
            is CustomMessages.SomethingWentWrong -> "Something Went Wrong"
            CustomMessages.Timeout -> "Timeout"
            CustomMessages.Unauthorized -> "Unauthorized"
        }
    }
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): DataResult<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
                ?: return DataResult.ErrorEvent(DataResult.CustomMessages.EmptyData)
            return DataResult.SuccessEvent(body)
        }
        return DataResult.ErrorEvent(
            handleException(response.code(), response.errorBody().toString())
        )
    } catch (e: Exception) {
        return DataResult.ErrorEvent(handleException(e))
    }
}