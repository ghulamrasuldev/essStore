package com.example.essstore.data

import android.util.Log
import com.example.essstore.common.Common.ACCESS_TOKEN
import com.example.essstore.common.Common.BASE_URL
import com.example.essstore.common.OAuthInterceptor
import okhttp3.OkHttpClient
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

    val api_two: ProductInterface by lazy<ProductInterface> {

        val client = OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor("Bearer", ACCESS_TOKEN))
            .build()

        Log.e("Request", "Sent")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductInterface::class.java)
    }
}
//