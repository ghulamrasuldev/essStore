package com.example.essstore.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.startActivity
import com.example.essstore.data.product

object Common {
    val paths = arrayOf("item 1", "item 2", "item 3")
    val DISPLAY_TIME_LONG: Long= 2000
    val DISPLAY_TIME_SHORT: Long = 1000
    val DISPLAY_TIME_TOO_LONG: Long = 3000
    val BASE_URL: String = "https://my.api.mockaroo.com/"
    const val GET_PRODUCTS : String = "products"
    const val GET_USER: String = "user"
    val API_KEY: String = "57b501f0"


    fun Context.nextScreenWithFinish(activity: Activity, classs : Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
        activity.finish()
    }

    fun Context.nextScreenWithoutFinish(activity: Activity, classs : Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
    }
}