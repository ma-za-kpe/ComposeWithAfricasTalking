package com.maku.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.core.data.local.model.CountryListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AirTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(articles: CountryListItem)

    @Query("SELECT * FROM country_list")
    fun getAllCountries(): Flow<List<CountryListItem>>

    @Query("SELECT * FROM country_list")
    fun getCountries(): List<CountryListItem>
}
