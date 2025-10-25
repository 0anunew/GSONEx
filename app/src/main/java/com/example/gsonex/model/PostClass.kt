package com.example.gsonex.model

import com.google.gson.annotations.SerializedName

data class Post (
    val userID: Int,

    val title: String?,
    @SerializedName("body") val text: String,
)
{
    val id: Int = 0
}

