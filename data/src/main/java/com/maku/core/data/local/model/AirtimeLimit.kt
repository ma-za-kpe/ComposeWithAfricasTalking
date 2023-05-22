package com.maku.core.data.local.model

import com.google.gson.annotations.SerializedName

data class AirtimeLimit(
    @SerializedName("lower")
    val lower: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("upper")
    val upper: Int
)
