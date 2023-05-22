package com.maku.core.data.local.repo

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.maku.core.data.R
import com.maku.core.data.local.dao.AirTimeDao
import com.maku.core.data.local.model.CountryList
import com.maku.core.data.local.model.CountryListItem
import com.maku.core.network.model.AirtimeLimitEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class LocalStorageImpl @Inject constructor(
    private val atDao: AirTimeDao,
    private val externalScope: CoroutineScope,
    @ApplicationContext private val context: Context
) : LocalStorage {

    init {
        insertIntoDb()
    }

    private fun insertIntoDb() {
        // TODO: this section needs to be optimized !!!
        // issue: this way of saving to the db has a limitation ...
        externalScope.launch(Dispatchers.IO) {
            try {
                if (atDao.getCountries().isEmpty()) {
                    for (i in 0 until loadJSONArray().length()) {
                        val item = loadJSONArray().getJSONObject(i)
                        val name = item.getString("name")
                        val dialCode = item.getString("dial_code")
                        val emoji = item.getString("emoji")
                        val code = item.getString("code")
                        val id = item.getInt("id")
                        val airtimeLimits = item.getJSONArray("airtime_limits")
                        val airtime = mutableListOf<AirtimeLimitEntity>()

                        for (a in 0 until airtimeLimits.length()) {
                            val limit: JSONObject = airtimeLimits.getJSONObject(a)
                            val telecom = limit.getString("name")
                            val lower = limit.getDouble("lower")
                            val upper = limit.getInt("upper")
                            airtime.add(
                                AirtimeLimitEntity(
                                    lower,
                                    telecom,
                                    upper
                                )
                            )
                        }
                        val countryListItem = CountryListItem(
                            airtime.toList(),
                            code,
                            dialCode,
                            emoji,
                            id,
                            name
                        )
                        atDao.insertCountries(countryListItem)
                    }
                }
            } catch (e: JSONException) {
                throw e
            }
        }
    }

    override fun getAllCountries(): Flow<List<CountryListItem>> {
        return atDao.getAllCountries()
    }

    private fun countryCode(): List<CountryListItem> {
        val jsonString = """
[{
		"name": "Kenya",
		"dial_code": "+254",
		"emoji": "🇰🇪",
		"code": "KE",
		"airtime_limits": [{
				"name": "Safaricom",
				"lower": 5,
				"upper": 10000
			},
			{
				"name": "Airtel",
				"lower": 10,
				"upper": 10000
			},
			{
				"name": "Telkom",
				"lower": 10,
				"upper": 10000
			}
		]
	},
	{
		"name": "Uganda",
		"dial_code": "+256",
		"emoji": "🇺🇬",
		"code": "UG",
		"airtime_limits": [{
			"name": "",
			"lower": 50,
			"upper": 200000
		}]
	},
	{
		"name": "Tanzania, United Republic of Tanzania",
		"dial_code": "+255",
		"emoji": "🇹🇿",
		"code": "TZ",
		"airtime_limits": [{
			"name": "",
			"lower": 500,
			"upper": 200000
		}]
	},
	{
		"name": "Nigeria",
		"dial_code": "+234",
		"emoji": "🇳🇬",
		"code": "NG",
		"airtime_limits": [{
			"name": "",
			"lower": 50,
			"upper": 20000
		}]
	},
	{
		"name": "Ethiopia",
		"dial_code": "+251",
		"emoji": "🇪🇹",
		"code": "ET",
		"airtime_limits": [{
			"name": "",
			"lower": 5,
			"upper": 5000
		}]
	},
	{
		"name": "Malawi",
		"dial_code": "+265",
		"emoji": "🇲🇼",
		"code": "MW",
		"airtime_limits": [{
				"name": "Airtel",
				"lower": 368,
				"upper": 73500
			},
			{
				"name": "TNM",
				"lower": 300,
				"upper": 30000
			}
		]
	},
	{
		"name": "Zambia",
		"dial_code": "+260",
		"emoji": "🇿🇲",
		"code": "ZM",
		"airtime_limits": [{
			"name": "",
			"lower": 5,
			"upper": 1000
		}]
	},
	{
		"name": "South Africa",
		"dial_code": "+27",
		"emoji": "🇿🇦",
		"code": "ZA",
		"airtime_limits": [{
			"name": "",
			"lower": 5,
			"upper": 1000
		}]
	},
	{
		"name": "Rwanda",
		"dial_code": "+250",
		"emoji": "🇷🇼",
		"code": "RW",
		"airtime_limits": [{
				"name": "Airtel",
				"lower": 100,
				"upper": 40000
			},
			{
				"name": "Tigo",
				"lower": 100,
				"upper": 40000
			},
			{
				"name": "MTN",
				"lower": 100,
				"upper": 500000
			}
		]
	},
	{
		"name": "Ghana",
		"dial_code": "+233",
		"emoji": "🇬🇭",
		"code": "GH",
		"airtime_limits": [{
				"name": "Airtel",
				"lower": 0.55,
				"upper": 1
			},
			{
				"name": "Tigo",
				"lower": 0.55,
				"upper": 1
			},
			{
				"name": "MTN",
				"lower": 1,
				"upper": 100
			},
			{
				"name": "Vodafone",
				"lower": 1,
				"upper": 100
			}
		]
	},
	{
		"name": "Cameroon",
		"dial_code": "+237",
		"emoji": "🇨🇲",
		"code": "CM",
		"airtime_limits": [{
				"name": "MTN",
				"lower": 100,
				"upper": 500000
			},
			{
				"name": "Next Tel",
				"lower": 150,
				"upper": 500000
			},
			{
				"name": "Orange",
				"lower": 150,
				"upper": 25000
			}
		]
	},
	{
		"name": "Ivory Coast",
		"dial_code": "+225",
		"emoji": "🇨🇮",
		"code": "XOF",
		"airtime_limits": [{
				"name": "Moov",
				"lower": 150,
				"upper": 1000000
			},
			{
				"name": "MTN",
				"lower": 500,
				"upper": 1000000
			},
			{
				"name": "Orange",
				"lower": 150,
				"upper": 1000000
			}
		]
	},
	{
		"name": "Senegal",
		"dial_code": "+221",
		"emoji": "🇸🇳",
		"code": "XOF",
		"airtime_limits": [{
			"name": "",
			"lower": 100,
			"upper": 60000
		}]
	}
]
    """.trimIndent()
        return Gson().fromJson(jsonString, CountryList::class.java).toList()
    }

    private fun loadJSONArray(): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.country_list)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}