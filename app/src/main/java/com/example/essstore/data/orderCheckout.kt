package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class orderCheckout(
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("products")
    val products: List<Int>,
    @SerializedName("userID")
    val userID: Int
)