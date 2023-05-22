package com.maku.core.data.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.core.network.model.AirtimeLimitEntity

class AtComposeConvertor {
    var gson = Gson()

    @TypeConverter
    fun airtimeLimitToString(airtimeLimits: List<AirtimeLimitEntity>): String{
        return gson.toJson(airtimeLimits)
    }

    @TypeConverter
    fun stringToAirtimeLimit(data: String): List<AirtimeLimitEntity>{
        val listType = object: TypeToken<List<AirtimeLimitEntity>>(){}.type
        return gson.fromJson(data, listType)
    }
}