package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityNotificationViewBinding

class NotificationView : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}