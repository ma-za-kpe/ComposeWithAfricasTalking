package com.maku.core.data.local.repo

import android.content.Context
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
        val jsonString = """  replace with json string   """.trimIndent()
        return Gson().fromJson(
            jsonString,
            CountryList::class.java
        ).toList()
    }

    private fun loadJSONArray(): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.country_list)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}