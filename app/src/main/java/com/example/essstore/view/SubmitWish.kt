package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivitySubmitWishBinding

class SubmitWish : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitWishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySubmitWishBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}