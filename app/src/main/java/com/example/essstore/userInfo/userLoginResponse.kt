package com.example.essstore.userInfo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userLoginInfo")
data class userLoginResponse(
    @SerializedName("email")
    val email: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("tokens")
    val tokens: String,
    @SerializedName("username")
    val username: String
)