package com.example.essstore.userInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class userLoginViewModel(application: Application): AndroidViewModel(application){
    val readAllData : LiveData<List<userLoginResponse>>
    private val repository : userLoginRepository

    init {
        val userDao = userLoginDatabase.getDatabase(application).userLoginDAO()
        repository = userLoginRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser (user: userLoginResponse){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun deleteUser(id: Int){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteUser(id)
        }
    }

    fun updateUser(id: Int, token: String){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.updateUser(id, token)
        }
    }

    fun deleteAll(){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteAll()
        }
    }
}