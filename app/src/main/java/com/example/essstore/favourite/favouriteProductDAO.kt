package com.example.essstore.favourite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.essstore.cart.cartProduct

@Dao
interface favouriteProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProductToFavourite (product: favouriteProduct)

    @Query("SELECT * FROM favourite ORDER BY productPrice ASC")
    fun readAllData () : LiveData<List<favouriteProduct>>

    @Query("DELETE FROM favourite WHERE id = :id")
    fun deleteProduct(id: Int)

    @Query("UPDATE favourite SET selectedQuantity =:selectedQuantity WHERE id=:id")
    fun UpdateProduct(id: Int, selectedQuantity: Int)
}