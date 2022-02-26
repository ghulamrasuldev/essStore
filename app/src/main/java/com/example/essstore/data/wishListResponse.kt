package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class wishListResponse(
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("productURL")
    val productURL: Any,
    @SerializedName("title")
    val title: String
)