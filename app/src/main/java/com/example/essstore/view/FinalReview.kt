package com.example.essstore.view

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityFinalReviewBinding
import android.app.PendingIntent

import androidx.core.app.NotificationCompat

import android.R
import android.os.Handler
import android.os.Looper
import com.example.essstore.common.Common


class FinalReview : AppCompatActivity() {
    private lateinit var binding: ActivityFinalReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextScreen()
    }

    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HotProducts::class.java)
            startActivity(intent)
            finish()
        }, Common.DISPLAY_TIME_LONG)
    }
}