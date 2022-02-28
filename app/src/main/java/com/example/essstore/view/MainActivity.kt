package com.example.essstore.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.essstore.R
import com.example.essstore.common.Common
import com.example.essstore.common.Common.DISPLAY_TIME_SHORT
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.common.Common.nextScreenWithFinishAndExtras
import com.example.essstore.data.user
import com.example.essstore.databinding.ActivityMainBinding
import com.example.essstore.userInfo.userLoginResponse
import com.example.essstore.userInfo.userLoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: userLoginViewModel
    var id = -1
    var token =""
    private val sharedPrefFile = "kotlinsharedpreference"


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        if(isUserLoggedIn()){
            nextScreenWithFinishAndExtras(this, HomeScreen::class.java, LOGIN_STATUS, LOGGED_IN)
        }else{
            addAnimation()
            nextScreen()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val sharedPref: SharedPreferences=  sharedPreferences
        id = sharedPref.getInt("id", -1)
        token = sharedPref.getString("token", "").toString()
        Common.ACCESS_TOKEN = token
        Common.USER_ID = id.toString()
        if (id == -1 && token == "" ){
            Log.d("ID: ", id.toString())
            Log.d("TOKEN: ", token)
            return false
        }
        return true
    }

    //This function is responsible to navigate to Welcome Screen
    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        },DISPLAY_TIME_SHORT)
    }

    //This function is responsible to apply animation on screen
    private fun addAnimation(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sampleText.animate().apply {
            duration = 250
            alpha(0F)
            translationY(50F)
        }.withEndAction{
            binding.sampleText.animate().apply {
                duration = 250
                alpha(1F)
                translationY(0F)
            }
        }.start()
    }
}