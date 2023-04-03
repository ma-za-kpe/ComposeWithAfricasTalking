package com.maku.core.data.repo

import com.maku.core.network.model.SendAirtime
import retrofit2.Response

// Abstractions for the different data sources, or services used in your app
interface ATComposeRepository {
    suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): Response<SendAirtime>
}
