package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class orderHistory(
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("products")
    val products: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("placed")
    val date: String
)