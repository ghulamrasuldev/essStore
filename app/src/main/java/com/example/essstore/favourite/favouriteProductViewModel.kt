package com.example.essstore.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class favouriteProductViewModel(application: Application): AndroidViewModel(application){
    val readAllData : LiveData<List<favouriteProduct>>
    private val repository : favouriteProductRepository

    init {
        val productDao = favouriteProductDatabase.getDatabase(application).favouriteProductDAO()
        repository = favouriteProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProductToFavourite (product: favouriteProduct){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProduct(product)
        }
    }

    fun deleteProduct(id: Int){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteProduct(id)
        }
    }

    fun updateProduct(id: Int, selectedQuanitity: Int){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.updateProduct(id, selectedQuanitity)
        }
    }
}