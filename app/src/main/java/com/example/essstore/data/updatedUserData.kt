package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class updatedUserData(
    @SerializedName("contactNo")
    val contactNo: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: Any?,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)