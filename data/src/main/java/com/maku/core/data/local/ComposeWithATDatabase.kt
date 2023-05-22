package com.maku.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maku.core.data.local.convertor.AtComposeConvertor
import com.maku.core.data.local.dao.AirTimeDao
import com.maku.core.data.local.model.CountryListItem

@Database(
    entities = [
        CountryListItem::class
    ],
    version = 1
)
@TypeConverters(AtComposeConvertor::class)
abstract class ComposeWithATDatabase : RoomDatabase() {
    abstract fun airtimeDao(): AirTimeDao
}
