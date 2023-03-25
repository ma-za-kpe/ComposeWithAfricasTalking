package com.maku.composewithafricastalking.core.data.entities.at

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.maku.composewithafricastalking.core.data.entities.at.Response

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
