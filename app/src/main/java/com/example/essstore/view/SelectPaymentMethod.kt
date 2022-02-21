package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.CASH_ON_DELIVERY
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.PAYMENT_METHOD
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtrasAndExtras
import com.example.essstore.databinding.ActivitySelectPaymentMethodBinding

class SelectPaymentMethod : AppCompatActivity() {
    private lateinit var STATUS: String
    private lateinit var binding: ActivitySelectPaymentMethodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()

        //Listeners
        binding.btnPaymentMethodCard.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                CardPayment::class.java,
                LOGIN_STATUS,
                STATUS
            )
        }

        binding.btnPaymentMethodCashOnDelivery.setOnClickListener{
            nextScreenWithoutFinishAndExtrasAndExtras(
                this,
                FinalReview::class.java,
                PAYMENT_METHOD,
                CASH_ON_DELIVERY,
                LOGIN_STATUS,
                STATUS
            )
        }

        binding.btnSelectPaymentBack.setOnClickListener{
            finish()
        }
    }
}
