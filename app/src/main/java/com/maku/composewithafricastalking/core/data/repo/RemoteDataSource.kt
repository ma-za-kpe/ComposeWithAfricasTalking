package com.maku.composewithafricastalking.core.data.repo

import com.maku.composewithafricastalking.core.data.entities.at.SendAirtime
import retrofit2.Response

interface RemoteDataSource {
    suspend fun sendAirtime(apiKey: String, name: String, recipients: String): Response<SendAirtime>
}