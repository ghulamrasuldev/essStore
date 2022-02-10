package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityHotProductsBinding

class HotProducts : AppCompatActivity() {
    private lateinit var binding: ActivityHotProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}