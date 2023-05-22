package com.maku.core.data.repo

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.core.data.local.repo.LocalStorage
import com.maku.core.data.remote.ATComposeApi
import com.maku.core.model.airtime.SendAirtime
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.network.model.AirtimeLimitEntity
import com.maku.core.network.repo.ATComposeRepository
import com.maku.core.state.NetworkResult
import java.lang.reflect.Type
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ATComposeRepositoryImpl @Inject constructor(
    private val api: ATComposeApi,
    private val localStorage: LocalStorage,
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
                    Log.d("TAG", "ATComposeRepository: 1 $response")
                    NetworkResult.Error(
                        code = response.code(),
                        message = response.body()?.errorMessage
                    )
                }
            } catch (e: HttpException) {
                Log.d("TAG", "ATComposeRepository HttpException: $e")
                NetworkResult.Error(code = e.code(), message = e.message())
            } catch (e: Throwable) {
                Log.d("TAG", "ATComposeRepository Throwable: $e")
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

//    override suspend fun getAllCountries(): List<AirtimeEntity> {
//        val jsonString = """
//[{
//		"name": "Kenya",
//		"dial_code": "+254",
//		"emoji": "🇰🇪",
//		"code": "KE",
//		"airtime_limits": [{
//				"name": "Safaricom",
//				"lower": 5,
//				"upper": 10000
//			},
//			{
//				"name": "Airtel",
//				"lower": 10,
//				"upper": 10000
//			},
//			{
//				"name": "Telkom",
//				"lower": 10,
//				"upper": 10000
//			}
//		]
//	},
//	{
//		"name": "Uganda",
//		"dial_code": "+256",
//		"emoji": "🇺🇬",
//		"code": "UG",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 50,
//			"upper": 200000
//		}]
//	},
//	{
//		"name": "Tanzania, United Republic of Tanzania",
//		"dial_code": "+255",
//		"emoji": "🇹🇿",
//		"code": "TZ",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 500,
//			"upper": 200000
//		}]
//	},
//	{
//		"name": "Nigeria",
//		"dial_code": "+234",
//		"emoji": "🇳🇬",
//		"code": "NG",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 50,
//			"upper": 20000
//		}]
//	},
//	{
//		"name": "Ethiopia",
//		"dial_code": "+251",
//		"emoji": "🇪🇹",
//		"code": "ET",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 5,
//			"upper": 5000
//		}]
//	},
//	{
//		"name": "Malawi",
//		"dial_code": "+265",
//		"emoji": "🇲🇼",
//		"code": "MW",
//		"airtime_limits": [{
//				"name": "Airtel",
//				"lower": 368,
//				"upper": 73500
//			},
//			{
//				"name": "TNM",
//				"lower": 300,
//				"upper": 30000
//			}
//		]
//	},
//	{
//		"name": "Zambia",
//		"dial_code": "+260",
//		"emoji": "🇿🇲",
//		"code": "ZM",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 5,
//			"upper": 1000
//		}]
//	},
//	{
//		"name": "South Africa",
//		"dial_code": "+27",
//		"emoji": "🇿🇦",
//		"code": "ZA",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 5,
//			"upper": 1000
//		}]
//	},
//	{
//		"name": "Rwanda",
//		"dial_code": "+250",
//		"emoji": "🇷🇼",
//		"code": "RW",
//		"airtime_limits": [{
//				"name": "Airtel",
//				"lower": 100,
//				"upper": 40000
//			},
//			{
//				"name": "Tigo",
//				"lower": 100,
//				"upper": 40000
//			},
//			{
//				"name": "MTN",
//				"lower": 100,
//				"upper": 500000
//			}
//		]
//	},
//	{
//		"name": "Ghana",
//		"dial_code": "+233",
//		"emoji": "🇬🇭",
//		"code": "GH",
//		"airtime_limits": [{
//				"name": "Airtel",
//				"lower": 0.55,
//				"upper": 1
//			},
//			{
//				"name": "Tigo",
//				"lower": 0.55,
//				"upper": 1
//			},
//			{
//				"name": "MTN",
//				"lower": 1,
//				"upper": 100
//			},
//			{
//				"name": "Vodafone",
//				"lower": 1,
//				"upper": 100
//			}
//		]
//	},
//	{
//		"name": "Cameroon",
//		"dial_code": "+237",
//		"emoji": "🇨🇲",
//		"code": "CM",
//		"airtime_limits": [{
//				"name": "MTN",
//				"lower": 100,
//				"upper": 500000
//			},
//			{
//				"name": "Next Tel",
//				"lower": 150,
//				"upper": 500000
//			},
//			{
//				"name": "Orange",
//				"lower": 150,
//				"upper": 25000
//			}
//		]
//	},
//	{
//		"name": "Ivory Coast",
//		"dial_code": "+225",
//		"emoji": "🇨🇮",
//		"code": "XOF",
//		"airtime_limits": [{
//				"name": "Moov",
//				"lower": 150,
//				"upper": 1000000
//			},
//			{
//				"name": "MTN",
//				"lower": 500,
//				"upper": 1000000
//			},
//			{
//				"name": "Orange",
//				"lower": 150,
//				"upper": 1000000
//			}
//		]
//	},
//	{
//		"name": "Senegal",
//		"dial_code": "+221",
//		"emoji": "🇸🇳",
//		"code": "XOF",
//		"airtime_limits": [{
//			"name": "",
//			"lower": 100,
//			"upper": 60000
//		}]
//	}
//]
//    """.trimIndent()
//        val listType: Type = object : TypeToken<ArrayList<AirtimeEntity?>?>() {}.type
//        return Gson().fromJson(jsonString, listType)
//    }
}
