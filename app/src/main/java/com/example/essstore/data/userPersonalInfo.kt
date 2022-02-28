package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class userPersonalInfo(
    @SerializedName("contactNo")
    val contactNo: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: Any,
    @SerializedName("image")
    val image: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)