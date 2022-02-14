package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class product(
    @SerializedName("quantity")
    val availableQuantity: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("created_at")
    val dateCreated: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val productDescription: String,
    @SerializedName("image")
    val productImage: String,
    @SerializedName("title")
    val productName: String,
    @SerializedName("price")
    val productPrice: Int,
    @SerializedName("sold_qt")
    val soldQuantity: Int
)