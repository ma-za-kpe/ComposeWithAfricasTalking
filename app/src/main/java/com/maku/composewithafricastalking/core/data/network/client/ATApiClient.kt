package com.maku.composewithafricastalking.core.data.network.client

import com.maku.composewithafricastalking.core.data.entities.at.SendAirtime
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ATApiClient {

    @FormUrlEncoded
    @POST("version1/airtime/send")
    suspend fun doAtSending(
        @Header("apiKey") apiKey: String,
        @Field("username") username: String?,
        @Field("recipients") recipients: String?
    ): Response<SendAirtime>
}
