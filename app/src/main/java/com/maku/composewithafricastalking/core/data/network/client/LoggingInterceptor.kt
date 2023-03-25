package com.maku.composewithafricastalking.core.data.network.client

import android.util.Log
import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d("TAG", "log: $message")
    }
}
