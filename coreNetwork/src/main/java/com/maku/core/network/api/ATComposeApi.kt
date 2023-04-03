package com.maku.core.network.api

import com.maku.core.network.model.SendAirtime
import com.maku.core.network.util.ApiConstants
import com.maku.core.network.util.ApiParameters
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ATComposeApi {
    @FormUrlEncoded
    @POST(ApiConstants.SEND_AIRTIME)
    suspend fun sendAirtime(
        @Header(ApiParameters.API_KEY) apiKey: String,
        @Field(ApiParameters.USERNAME) username: String?,
        @Field(ApiParameters.RECIPIENTS) recipients: String?
    ): Response<SendAirtime>
}
