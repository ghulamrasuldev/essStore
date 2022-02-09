package com.example.essstore

import android.content.Intent
import android.graphics.Color.alpha
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sampleText.animate().apply {
            duration = 250
            alpha(0F)
            translationY(100F)
        }.withEndAction{
            binding.sampleText.animate().apply {
                duration = 250
                alpha(1F)
                translationY(0F)
            }
        }.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        },1000)

    }
}