package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityFavouriteProductsBinding

class FavouriteProducts : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}