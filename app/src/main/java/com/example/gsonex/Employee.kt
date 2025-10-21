package com.example.gsonex

import com.google.gson.annotations.*

data class Employee(
    @SerializedName("address") val mAddress: Address,
    @SerializedName("age") val mAge: Int,
    @Expose(serialize = true) val mFirstName: String,
    @Expose(serialize = true) val mEmail: String,
    @SerializedName("family") val mFamilyMembers: Array<FamilyMember>,
    @Expose(serialize = false) val mPassword: String
)

