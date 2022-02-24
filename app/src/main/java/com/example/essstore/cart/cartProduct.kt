package com.example.essstore.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productsInCart")
data class cartProduct (
    @PrimaryKey
    val id: Int,
    val availableQuantity: Int,
    val category: String,
    val dateCreated: String ? ,
    val discount: Int,
    val productDescription: String,
    val productImage: String,
    val productName: String,
    val productPrice: Int,
    val soldQuantity: Int,
    var selectedQuantity: Int,
    var img: String
)