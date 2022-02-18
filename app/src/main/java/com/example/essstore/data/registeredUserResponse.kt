package com.example.essstore.data

import com.google.gson.annotations.SerializedName

data class registeredUserResponse (
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String
)
