package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.databinding.ActivitySelectPaymentMethodBinding

class SelectPaymentMethod : AppCompatActivity() {

    private lateinit var binding: ActivitySelectPaymentMethodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Listeners

        binding.btnPaymentMethodCard.setOnClickListener{
            nextScreenWithFinish(
                this,
                CardPayment::class.java
            )
        }

        binding.btnPaymentMethodCashOnDelivery.setOnClickListener{
            nextScreenWithFinish(
                this,
                FinalReview::class.java
            )
        }
    }
}