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




class FinalReview : AppCompatActivity() {
    private lateinit var binding: ActivityFinalReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}