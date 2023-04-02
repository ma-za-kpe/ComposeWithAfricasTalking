package com.maku.core.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendAirtime {
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null

    @SerializedName("numSent")
    @Expose
    var numSent: Int? = null

    @SerializedName("totalAmount")
    @Expose
    var totalAmount: String? = null

    @SerializedName("totalDiscount")
    @Expose
    var totalDiscount: String? = null

    @SerializedName("responses")
    @Expose
    var responses: List<Response>? = null
}
