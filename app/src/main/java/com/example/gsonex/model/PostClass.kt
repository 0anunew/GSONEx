package com.example.gsonex.model

import com.google.gson.annotations.SerializedName

data class Post (
    val userID: Int,
    val id: Int,
    val title: String,
    @SerializedName("body") val text: String,
)

