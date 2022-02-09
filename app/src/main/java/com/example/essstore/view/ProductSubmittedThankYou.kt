package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.common.Common
import com.example.essstore.databinding.ActivityProductSubmittedThankYouBinding

class ProductSubmittedThankYou : AppCompatActivity() {
    private lateinit var binding: ActivityProductSubmittedThankYouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductSubmittedThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nextScreen()
    }

    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OrderCreatedThankYou::class.java)
            startActivity(intent)
            finish()
        }, Common.DISPLAY_TIME_LONG)
    }
}