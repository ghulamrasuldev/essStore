package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class orderHistoryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("placed")
    val placed: String,
    @SerializedName("products")
    val products: List<ProductX>
)