package com.example.essstore.favourite

import androidx.lifecycle.LiveData
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductDAO

class favouriteProductRepository (private val favouriteProductDAO: favouriteProductDAO){

    val readAllData : LiveData<List<favouriteProduct>> = favouriteProductDAO.readAllData()

    suspend fun addProduct(product: favouriteProduct){
        favouriteProductDAO.addProductToFavourite(product)
    }

    suspend fun updateProduct(id: Int, selectedQuanitity: Int){
        favouriteProductDAO.UpdateProduct(id, selectedQuanitity)
    }

    suspend fun deleteProduct(id: Int){
        favouriteProductDAO.deleteProduct(id)
    }

}