package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.common.Common
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.databinding.ActivityOrderCreatedThankYouBinding

class OrderCreatedThankYou : AppCompatActivity() {
    private lateinit var binding: ActivityOrderCreatedThankYouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderCreatedThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            nextScreenWithFinish(
                this,
                HomeScreen::class.java
            )
        }, Common.DISPLAY_TIME_LONG)
    }
}