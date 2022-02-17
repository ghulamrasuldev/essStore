package com.example.essstore.data

import com.example.essstore.common.Common.GET_PRODUCTS
import com.example.essstore.common.Common.GET_USER
import retrofit2.Response
import retrofit2.http.*

interface ProductInterface {
    @GET(GET_PRODUCTS)
    suspend fun getProducts(@Query("key") key: String): Response<List<product>>

    @GET(GET_USER)
    suspend fun getUser(@Query("key") key: String): Response<user>
}

//@Query("key") key: String