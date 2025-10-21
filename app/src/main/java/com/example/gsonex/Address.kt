package com.example.gsonex

import com.google.gson.annotations.SerializedName
data class Address(
    @SerializedName("street") val mStreet: String,
    @SerializedName("city") val mCity: String,
    @SerializedName("state") val mState: String,
    @SerializedName("zip") val mZip: String
)
