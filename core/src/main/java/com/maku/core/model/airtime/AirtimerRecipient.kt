package com.maku.core.model.airtime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class AirtimerRecipient {
    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("amount")
    @Expose
    var amount: String? = null
}
