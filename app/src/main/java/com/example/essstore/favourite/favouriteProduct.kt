package com.example.essstore.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class favouriteProduct (
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
    var selectedQuantity: Int
)