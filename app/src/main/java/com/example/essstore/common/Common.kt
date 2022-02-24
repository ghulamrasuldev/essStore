package com.example.essstore.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.StrictMode
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.essstore.view.Login
import java.util.*
import java.util.regex.Pattern
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

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
    const val PRODUCT_ADDED_TO_CART = "Product Added to Cart!"
    const val PRODUCT_ADDED_TO_FAVOURITE = "Product Added to Favourites!"
    const val INVALID_CODE = "You entered an invalid code!"
    const val INVALID_EMIAL ="Provided email is invalid!"
    const val INVALID_PHONE ="Provided Phone is invalid"
    const val CHECK_EMAIL= "Please check your email for Varification-code"
    const val EMPTY_FIELD = "There are some Empty Fields!"
    const val PHONE_REGX = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}\$|^\\d{11}\$|^\\d{4}-\\d{7}\$"

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
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun sendEmail(email: String): String{
        //authentication info
        val username = "ghulamrasool.testing@yahoo.com"
        val password = "vtmjjqhvheybeeso"
        val fromEmail = "ghulamrasool.testing@yahoo.com"
        val toEmail = email
        val code = (100000..999999).random()
        val properties = Properties()
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.smtp.host"] = "smtp.mail.yahoo.com"
        properties["mail.smtp.port"] = "587"
        Log.d("Email Status: ","Sending")
        Log.d("Email Status: ","$email")

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
        //Start our mail message
        //Start our mail message
        val msg = MimeMessage(session)
        try {
            msg.setFrom(InternetAddress(fromEmail))
            msg.addRecipient(Message.RecipientType.TO, InternetAddress(toEmail))
            msg.subject = "Verification Code for ESS"
            val emailContent: Multipart = MimeMultipart()
            //Text body part
            val textBodyPart = MimeBodyPart()
            textBodyPart.setText("Your 6-Digit verification code is $code")
            //Attach body parts
            emailContent.addBodyPart(textBodyPart)
            //Attach multipart to message
            msg.setContent(emailContent)
            Transport.send(msg)
            Log.d("Email Status: ","Sent")
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
        return "$code"
    }

    fun isEmailValid(email: String): Boolean{
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    fun isValidPhone(phone: String): Boolean{
        return Pattern.compile(PHONE_REGX).matcher(phone).matches()
    }
}