package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("category")
    val category: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("sold_qt")
    val soldQt: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: Any
)