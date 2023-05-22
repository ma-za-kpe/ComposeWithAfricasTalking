package com.maku.core.data.local.repo

import com.maku.core.data.local.model.CountryListItem
import kotlinx.coroutines.flow.Flow

interface LocalStorage {
    fun getAllCountries(): Flow<List<CountryListItem>>
}