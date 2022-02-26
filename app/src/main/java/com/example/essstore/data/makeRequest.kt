package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class makeRequest(
    @SerializedName("brand_name")
    val brandName: Any?,
    @SerializedName("productURL")
    val productURL: Any?,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_id")
    val user: Int
)