package com.example.essstore.data

import com.example.essstore.common.Common.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.*

interface ProductInterface {
    @GET(GET_PRODUCTS)
    suspend fun getTodos(): Response<List<product>>
}

//@Query("key") key: String