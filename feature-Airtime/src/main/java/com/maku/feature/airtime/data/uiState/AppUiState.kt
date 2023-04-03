package com.maku.feature.airtime.data.uiState

data class ForSelfAirtimeUiState(
    val amount: String = "",
    val error: Boolean = false,
    val loading: Boolean = false
)

data class ForAnotherAirtimeUiState(
    val amount: String = "",
    val phone: String = "",
    val error: Boolean = false,
    val loading: Boolean = false
)
