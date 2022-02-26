package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.essstore.common.Common.DISPLAY_TIME_SHORT
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.data.user
import com.example.essstore.databinding.ActivityMainBinding
import com.example.essstore.userInfo.userLoginResponse
import com.example.essstore.userInfo.userLoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: userLoginViewModel
    var id = -1
    var token =""

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        if(isUserLoggedIn()){
            nextScreenWithFinish(this, HomeScreen::class.java)
        }else{
            addAnimation()
            nextScreen()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        var userL: userLoginResponse? = null
        mUserViewModel.readAllData.observe(this, androidx.lifecycle.Observer {users->
            for (user in users){
                userL = user
            }
        })
        return userL!=null
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