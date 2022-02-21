package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.CASH_ON_DELIVERY
import com.example.essstore.common.Common.PAYMENT_METHOD
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.databinding.ActivitySelectPaymentMethodBinding

class SelectPaymentMethod : AppCompatActivity() {

    private lateinit var binding: ActivitySelectPaymentMethodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Listeners
        binding.btnPaymentMethodCard.setOnClickListener{
            nextScreenWithoutFinish(
                this,
                CardPayment::class.java,
            )
        }

        binding.btnPaymentMethodCashOnDelivery.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                FinalReview::class.java,
                PAYMENT_METHOD,
                CASH_ON_DELIVERY
            )
        }

        binding.btnSelectPaymentBack.setOnClickListener{
            finish()
        }
    }
}