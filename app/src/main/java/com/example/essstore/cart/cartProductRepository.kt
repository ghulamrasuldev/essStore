package com.example.essstore.cart

import androidx.lifecycle.LiveData

class cartProductRepository (private val cartProductDAO: cartProductDAO){

    val readAllData : LiveData<List<cartProduct>> = cartProductDAO.readAllData()

    suspend fun addProduct(product: cartProduct){
        cartProductDAO.addProductToCart(product)
    }

}