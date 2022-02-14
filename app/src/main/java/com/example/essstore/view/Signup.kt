package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.common.Common.DISPLAY_TIME_LONG
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignupScreenSignup.setOnClickListener{
            nextScreenWithFinish(this, HomeScreen::class.java)
        }

        binding.btnSignupScreenBack.setOnClickListener{
            finish()
        }
    }
}