package com.example.uas_pm


import com.google.gson.annotations.SerializedName

data class BodyPost(
    @SerializedName("body")
    val body: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)

data class BodyPut(
    @SerializedName("body")
    val body: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int
)