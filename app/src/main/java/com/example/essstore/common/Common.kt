package com.example.essstore.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.essstore.view.Login

object Common {
    val DISPLAY_TIME_LONG: Long= 2000
    val DISPLAY_TIME_SHORT: Long = 1000
    val DISPLAY_TIME_TOO_LONG: Long = 3000

    //http://192.168.18.59:3000/
    //https://my.api.mockaroo.com/
    val BASE_URL: String = "https://my.api.mockaroo.com/"
    val BASE_URL_TWO: String = "https://my.api.mockaroo.com/"
    const val GET_PRODUCTS : String = "products"
    const val HOT_PRODUCTS: String = "hot-products/"
    const val GET_PROMOTIONS: String = "promotions/"
    const val NEW_ARRIVALS: String ="new-arrivals/"
    const val ALL_PRODUCTS: String = "all-products/"
    const val GET_WISH_LIST: String = "wish-list/"
    const val GET_USER: String = "user"
    const val REGISTER_USER: String = "register/"
    const val LOGIN_USER: String = "login/"
    //57b501f0
    const val API_KEY: String = "57b501f0"
    const val PAYMENT_METHOD: String = "Payment Method"
    const val CASH_ON_DELIVERY: String = "Cash on Delivery"
    const val CARD_PAYMENT: String = "Card Payment"
    const val LOGIN_STATUS: String = "Login Status"
    const val LOGGED_IN: String = "Logged in"
    const val NOT_LOGGED_IN: String = "Not Logged in"


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

    fun Context.nextScreenWithoutFinish(activity: Context, classs: Class<*>) {
        val intent = Intent(activity, classs)
        startActivity(intent)
    }

    fun Context.nextScreenWithoutFinishAndExtras(activity: Activity, classs : Class<*>, key : String, value: String) {
        val intent = Intent(activity, classs)
        intent.putExtra(key, value)
        startActivity(intent)
    }

    fun Context.nextScreenWithFinishAffinityAndExtras(activity: Activity, classs: Class<*>, key: String, value: String) {
        val intent = Intent(activity, classs)
        intent.putExtra(key, value)
        startActivity(intent)
        activity.finishAffinity()
    }

    fun Context.nextScreenWithoutFinishAndExtrasAndExtras(activity: Activity, classs : Class<*>, key : String, value: String, key2: String, value2: String) {
        val intent = Intent(activity, classs)
        intent.putExtra(key, value)
        intent.putExtra(key2, value2)
        startActivity(intent)
    }

    fun makeToast (context: Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }
}