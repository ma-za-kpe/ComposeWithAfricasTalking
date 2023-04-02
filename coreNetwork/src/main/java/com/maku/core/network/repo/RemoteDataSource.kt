package com.maku.core.network.repo

import com.maku.core.network.model.SendAirtime
import com.maku.core.network.state.ApiResult

interface RemoteDataSource {
    suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): ApiResult<SendAirtime>
}
