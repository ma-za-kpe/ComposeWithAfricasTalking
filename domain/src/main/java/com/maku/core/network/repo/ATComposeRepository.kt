package com.maku.core.network.repo

import com.maku.core.model.airtime.SendAirtime
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.state.ApiResult
import kotlinx.coroutines.flow.Flow

interface ATComposeRepository {
    suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): ApiResult<SendAirtime>

    suspend fun getAllCountries(): Flow<List<AirtimeEntity>>
}
