package com.example.essstore.cart

import androidx.lifecycle.LiveData

class cartProductRepository (private val cartProductDAO: cartProductDAO){

    val readAllData : LiveData<List<cartProduct>> = cartProductDAO.readAllData()

    suspend fun addProduct(product: cartProduct){
        cartProductDAO.addProductToCart(product)
    }

    suspend fun updateProduct(id: Int, selectedQuanitity: Int){
        cartProductDAO.UpdateProduct(id, selectedQuanitity)
    }

    suspend fun deleteProduct(id: Int){
        cartProductDAO.deleteProduct(id)
    }

}