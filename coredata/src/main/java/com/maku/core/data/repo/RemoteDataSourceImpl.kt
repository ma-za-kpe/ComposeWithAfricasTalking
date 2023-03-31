package com.maku.core.data.repo

import com.maku.core.network.data.SendAirtime

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): SendAirtime {
        TODO("Not yet implemented")
    }
}
