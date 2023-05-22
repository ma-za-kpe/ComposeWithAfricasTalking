package com.maku.core.network.repo

import com.maku.core.model.airtime.SendAirtime
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.state.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ATComposeRepository {
    suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): NetworkResult<SendAirtime>
    suspend fun getAllCountries():  Flow<List<AirtimeEntity>>
}
