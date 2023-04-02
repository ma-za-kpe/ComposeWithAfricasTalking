package com.maku.core.network.state

// TODO: replace this implementation with Arrow implementation => https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/
sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
}
