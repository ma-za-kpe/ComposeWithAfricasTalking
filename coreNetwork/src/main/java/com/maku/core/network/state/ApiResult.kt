package com.maku.core.network.state

import com.maku.core.network.model.SendAirtime

// TODO: replace this implementation with Arrow implementation => https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/
sealed class ApiResult<T>( // generic
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ApiResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : ApiResult<T>(data, message)
    class Loading<T> : ApiResult<T>()
}


//sealed class ApiResult<out R> {
//    data class Success<out T>(val data: T) : ApiResult<T>()
//    data class Error(val exception: Exception) : ApiResult<Nothing>()
//}
