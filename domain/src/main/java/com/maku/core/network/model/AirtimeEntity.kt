package com.maku.core.network.model

data class AirtimeEntity(
    val airtimeLimits: List<AirtimeLimitEntity>,
    val code: String,
    val dialCode: String,
    val emoji: String,
    val id: Int,
    val name: String
)
