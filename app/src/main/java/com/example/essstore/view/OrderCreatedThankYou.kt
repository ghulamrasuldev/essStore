package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityOrderCreatedThankYouBinding

class OrderCreatedThankYou : AppCompatActivity() {
    private lateinit var binding: ActivityOrderCreatedThankYouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderCreatedThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}