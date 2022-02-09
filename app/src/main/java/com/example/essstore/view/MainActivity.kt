package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addAnimation()
        nextScreen()
    }

    //This function is responsible to navigate to Welcome Screen
    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        },1000)
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