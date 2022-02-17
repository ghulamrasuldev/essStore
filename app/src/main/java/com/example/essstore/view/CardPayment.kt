package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.CARD_PAYMENT
import com.example.essstore.common.Common.PAYMENT_METHOD
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.databinding.ActivityCardPaymentBinding

class CardPayment : AppCompatActivity() {

    private lateinit var binding: ActivityCardPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardPaymentProceed.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                FinalReview::class.java,
                PAYMENT_METHOD,
                CARD_PAYMENT
            )
        }

        binding.btnCardInfoBack.setOnClickListener{
            finish()
        }
    }
}