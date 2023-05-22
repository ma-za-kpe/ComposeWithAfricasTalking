package com.maku.core.data.repo

import com.maku.core.data.local.repo.LocalStorage
import com.maku.core.data.remote.ATComposeApi
import com.maku.core.model.airtime.SendAirtime
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.network.repo.ATComposeRepository
import com.maku.core.state.NetworkResult
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ATComposeRepositoryImpl @Inject constructor(
    private val api: ATComposeApi,
    private val localStorage: LocalStorage
) : ATComposeRepository {
    override suspend fun sendAirtime(
        apiKey: String,
        name: String,
        recipients: String
    ): NetworkResult<SendAirtime> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.sendAirtime(
                    apiKey,
                    name,
                    recipients
                )
                val body = response.body()
                if (response.isSuccessful && body != null && body.errorMessage != "None") {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error(
                        code = response.code(),
                        message = response.body()?.errorMessage
                    )
                }
            } catch (e: HttpException) {
                NetworkResult.Error(
                    code = e.code(),
                    message = e.message()
                )
            } catch (e: Throwable) {
                NetworkResult.Exception(e)
            }
        }
    }

    override suspend fun getAllCountries(): Flow<List<AirtimeEntity>> {
        return localStorage.getAllCountries()
            .map { it ->
                it.map {
                    it.toDomain()
                }
            }
    }
}
