package com.example.essstore.userInfo

import androidx.lifecycle.LiveData
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductDAO

class userLoginRepository (private val userLoginDAO: userLoginDAO){

    val readAllData : LiveData<List<userLoginResponse>> = userLoginDAO.readAllData()

    suspend fun addUser(user: userLoginResponse){
        userLoginDAO.addUserInfo(user)
    }

    suspend fun updateUser(id: Int, token: String){
        userLoginDAO.updateUser(id, token)
    }

    suspend fun deleteUser(id: Int){
        userLoginDAO.deleteUser(id)
    }
    suspend fun deleteAll(){
        userLoginDAO.deleteAll()
    }

}