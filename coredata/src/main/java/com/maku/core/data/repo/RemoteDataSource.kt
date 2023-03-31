package com.maku.core.data.repo

import com.maku.core.network.data.SendAirtime


interface RemoteDataSource {
    suspend fun sendAirtime(
        apiKey: String,
        name: String, recipients: String
    ): SendAirtime
}