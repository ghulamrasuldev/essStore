package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityLatestProductsBinding

class LatestProducts : AppCompatActivity() {
    private lateinit var binding: ActivityLatestProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLatestProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}