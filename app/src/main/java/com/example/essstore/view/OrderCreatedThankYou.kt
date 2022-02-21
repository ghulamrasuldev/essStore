package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
import com.example.essstore.databinding.ActivityOrderCreatedThankYouBinding

class OrderCreatedThankYou : AppCompatActivity() {
    private lateinit var STATUS: String
    private lateinit var binding: ActivityOrderCreatedThankYouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderCreatedThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        Handler(Looper.getMainLooper()).postDelayed({
            nextScreenWithFinishAffinityAndExtras(
                this,
                HomeScreen::class.java,
                LOGIN_STATUS,
                STATUS
            )
        }, Common.DISPLAY_TIME_LONG)
    }
}