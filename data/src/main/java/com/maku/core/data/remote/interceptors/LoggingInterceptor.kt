package com.maku.core.data.remote.interceptors

import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.d("log: $message")
    }
}
