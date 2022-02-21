package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.databinding.ActivityGetPersonalInfoBinding

class GetPersonalInfo : AppCompatActivity() {
    private lateinit var binding : ActivityGetPersonalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getPersonalInfoVerify.setOnClickListener{
            binding.getPersonalInfoVerificationPart.isVisible = true
        }

        binding.getPersonalInfoProceed.setOnClickListener{
            nextScreenWithoutFinish(this, SelectPaymentMethod::class.java)
        }


    }
}