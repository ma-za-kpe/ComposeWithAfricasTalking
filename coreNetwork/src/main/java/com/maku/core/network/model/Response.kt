package com.maku.core.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Response {
    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null

    @SerializedName("amount")
    @Expose
    var amount: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("requestId")
    @Expose
    var requestId: String? = null

    @SerializedName("discount")
    @Expose
    var discount: String? = null
}
