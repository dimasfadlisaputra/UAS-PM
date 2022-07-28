package com.example.uas_pm


import com.google.gson.annotations.SerializedName

data class ResponseAddPost(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)