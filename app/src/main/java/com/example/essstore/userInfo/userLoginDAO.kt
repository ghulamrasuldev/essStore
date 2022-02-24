package com.example.essstore.userInfo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.essstore.favourite.favouriteProduct

@Dao
interface userLoginDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUserInfo (user: userLoginResponse)

    @Query("SELECT * FROM userLoginInfo ORDER BY id ASC")
    fun readAllData () : LiveData<List<userLoginResponse>>

    @Query("DELETE FROM userLoginInfo WHERE id = :id")
    fun deleteUser(id: Int)

    @Query("UPDATE userLoginInfo SET tokens=:token WHERE id=:id")
    fun updateUser(id: Int, token: String)

    @Query("DELETE FROM userLoginInfo")
    fun deleteAll()

}