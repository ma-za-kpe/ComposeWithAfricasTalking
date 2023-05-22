package com.maku.core.data.remote

import com.maku.core.model.airtime.SendAirtime
import com.maku.core.util.ApiConstants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ATComposeApi {
    @FormUrlEncoded
    @POST(ApiConstants.SEND_AIRTIME)
    suspend fun sendAirtime(
        @Header(com.maku.core.util.ApiParameters.API_KEY) apiKey: String,
        @Field(com.maku.core.util.ApiParameters.USERNAME) username: String?,
        @Field(com.maku.core.util.ApiParameters.RECIPIENTS) recipients: String?
    ): Response<SendAirtime>
}
