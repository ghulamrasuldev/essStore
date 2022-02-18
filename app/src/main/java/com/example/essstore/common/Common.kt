package com.example.essstore.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.essstore.data.product

object Common {
    val DISPLAY_TIME_LONG: Long= 2000
    val DISPLAY_TIME_SHORT: Long = 1000
    val DISPLAY_TIME_TOO_LONG: Long = 3000

    //http://192.168.18.59:3000/
    //https://my.api.mockaroo.com/
    val BASE_URL: String = "http://192.168.18.59:3000/"
    const val GET_PRODUCTS : String = "products"
    const val HOT_PRODUCTS: String = "hot-products/"
    const val GET_PROMOTIONS: String = "promotions/"
    const val NEW_ARRIVALS: String ="new-arrivals/"
    const val ALL_PRODUCTS: String = "all-products/"
    const val GET_WISH_LIST: String = "wish-list/"
    const val GET_USER: String = "user"
    const val REGISTER_USER: String = "register/"
    //57b501f0
    const val API_KEY: String = "57b501f0"
    const val PAYMENT_METHOD: String = "Payment Method"
    const val CASH_ON_DELIVERY: String = "Cash on Delivery"
    const val CARD_PAYMENT: String = "Card Payment"


    fun Context.nextScreenWithFinish(activity: Activity, classs : Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
        activity.finish()
    }

    fun Context.nextScreenWithFinishAffinity(activity: Activity, classs : Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
        activity.finishAffinity()
    }

    fun Context.nextScreenWithoutFinish(activity: Activity, classs : Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
    }

    fun Context.nextScreenWithoutFinishAndExtras(activity: Activity, classs : Class<*>, key : String, value: String) {
        val intent = Intent(activity, classs)
        intent.putExtra(key, value)
        startActivity(intent)
    }

    fun makeToast (context: Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }
}