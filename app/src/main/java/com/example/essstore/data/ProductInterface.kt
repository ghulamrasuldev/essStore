package com.example.essstore.data

import retrofit2.Response
import retrofit2.http.GET

interface ProductInterface {
    @GET("/todos")
    suspend fun getTodos(): Response<List<product>>
}

