package com.maku.airtime.data.uiState

import com.maku.core.data.local.model.AirtimeLimit
import com.maku.core.network.model.AirtimeEntity
import com.maku.core.network.model.AirtimeLimitEntity

data class ForSelfAirtimeUiState(
    val amount: String = "",
    val error: Boolean = false,
    val loading: Boolean = false
)

data class ForAnotherAirtimeUiState(
    val amount: String = "7000",
    // TODO: Set default country code from th vm
    val dial_code: String = "\uD83C\uDDFA\uD83C\uDDEC +256", //
    val airtimeLimit: String = "(50 - 200000)",
    val phone: String = "0700000000",
    val error: Boolean = false,
    val loading: Boolean = false,
    val airtimeCountryList: List<AirtimeEntity> = emptyList(),
    val airtimeLimitList: List<AirtimeLimitEntity> = emptyList()
)

data class CountryUiState(
    val name: String = "",
    val dial_code: String = "",
    val emoji: String = "",
    val code: String = "",
    val airtime_limits: List<AirtimeLimit> = emptyList()
)
