package com.example.essstore.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class cartProductViewModel(application: Application): AndroidViewModel(application){
    val readAllData : LiveData<List<cartProduct>>
    private val repository : cartProductRepository

    init {
        val productDao = cartProductDatabase.getDatabase(application).cartProductDAO()
        repository = cartProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProductToCart (product: cartProduct){
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

    fun deleteAll(){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}