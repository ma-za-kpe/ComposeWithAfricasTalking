package com.maku.core.data.repo

import com.maku.core.network.api.ATComposeApi
import com.maku.core.network.model.SendAirtime
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ATComposeRepositoryImpl @Inject constructor(
    private val api: ATComposeApi
) : ATComposeRepository {
    override suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): Response<SendAirtime> {
        return withContext(Dispatchers.IO) {
            api.sendAirtime(
                apiKey,
                name,
                recipients
            )
        }
    }
}
