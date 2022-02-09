package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.R
import com.example.essstore.common.Common.DISPLAY_TIME_LONG
import com.example.essstore.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nextScreen()
    }
    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        },DISPLAY_TIME_LONG)
    }
}