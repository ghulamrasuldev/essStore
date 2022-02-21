package com.example.essstore.data


import com.google.gson.annotations.SerializedName

data class registerUser(
    var email: String,
    var username: String,
    var password: String
)