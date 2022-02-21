package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.databinding.ActivityGetPersonalInfoBinding

class GetPersonalInfo : AppCompatActivity() {
    private lateinit var STATUS: String
    private lateinit var binding : ActivityGetPersonalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()

        //Listeners
        binding.getPersonalInfoVerify.setOnClickListener{
            binding.getPersonalInfoVerificationPart.isVisible = true
        }

        binding.getPersonalInfoBack.setOnClickListener{
            finish()
        }

        binding.getPersonalInfoProceed.setOnClickListener{
            nextScreenWithoutFinishAndExtras(this, SelectPaymentMethod::class.java, LOGIN_STATUS, STATUS)
        }
    }
}