package com.example.uas_pm


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class ResponsePost : ArrayList<ResponsePost.ResponsePostItem>(){
    @Parcelize
    data class ResponsePostItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    ):Parcelable
}