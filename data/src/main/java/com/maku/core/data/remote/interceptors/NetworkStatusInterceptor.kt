package com.maku.core.data.remote.interceptors

import com.maku.core.data.remote.internet.ConnectionManager
import com.maku.core.network.exception.NetworkUnavailableException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

// code reference: https://www.kodeco.com/books/real-world-android-by-tutorials/v2.0/chapters/4-data-layer-network
class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}
