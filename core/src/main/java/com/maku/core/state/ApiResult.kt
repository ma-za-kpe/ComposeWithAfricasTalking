package com.maku.core.state

// TODO: replace this implementation with Arrow implementation => https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val data: T) : NetworkResult<T>()
    // TODO: group these error message classes
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}


