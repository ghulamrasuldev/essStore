package com.example.essstore.cart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface cartProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProductToCart (product: cartProduct)

    @Query("SELECT * FROM productsInCart ORDER BY productPrice ASC")
    fun readAllData () : LiveData<List<cartProduct>>
}