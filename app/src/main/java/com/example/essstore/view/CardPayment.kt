package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.databinding.ActivityCardPaymentBinding

class CardPayment : AppCompatActivity() {

    private lateinit var binding: ActivityCardPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardPaymentProceed.setOnClickListener{
            nextScreenWithoutFinish(
                this,
                FinalReview::class.java
            )
        }
    }
}