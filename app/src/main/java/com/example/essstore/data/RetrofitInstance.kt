package com.example.essstore.data

import android.util.Log
import com.example.essstore.common.Common.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ProductInterface by lazy<ProductInterface> {
        Log.e("Request", "Sent")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductInterface::class.java)
    }
}
//