package com.example.essstore.data

import retrofit2.Response
import retrofit2.http.*

interface ProductInterface {
    @GET("products")
    suspend fun getTodos(@Query("key") key: String): Response<List<product>>
}

