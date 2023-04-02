package com.maku.core.network.repo

import com.maku.core.network.api.ATComposeApi
import com.maku.core.network.model.SendAirtime
import com.maku.core.network.state.ApiResult
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl @Inject constructor(
    private val api: ATComposeApi
): RemoteDataSource {
    override suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): ApiResult<SendAirtime> {
        return withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
    }
}
