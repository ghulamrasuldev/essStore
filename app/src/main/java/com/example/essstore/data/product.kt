package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class product(
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("Category")
    val category: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("product_description")
    val productDescription: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_price")
    val productPrice: Int,
    @SerializedName("sold_quantity")
    val soldQuantity: Int
)