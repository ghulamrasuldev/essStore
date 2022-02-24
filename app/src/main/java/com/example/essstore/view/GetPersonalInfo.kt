package com.example.essstore.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.core.view.isVisible
import com.example.essstore.common.Common.CHECK_EMAIL
import com.example.essstore.common.Common.EMPTY_FIELD
import com.example.essstore.common.Common.INVALID_CODE
import com.example.essstore.common.Common.INVALID_EMIAL
import com.example.essstore.common.Common.INVALID_PHONE
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.isEmailValid
import com.example.essstore.common.Common.isValidPhone
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.common.Common.sendEmail
import com.example.essstore.databinding.ActivityGetPersonalInfoBinding
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import kotlin.random.Random.Default.nextInt

class GetPersonalInfo : AppCompatActivity() {
    private lateinit var STATUS: String
    var code: String = ""
    private lateinit var binding : ActivityGetPersonalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()

        //Listeners
        binding.getPersonalInfoVerify.setOnClickListener{
            if(isEmailValid(binding.getPersonalInfoEmail.text.toString())
                && isValidPhone(binding.getPersonalInfoPhone.text.toString())
                && binding.getPersonalInfoName.text.toString().isNotEmpty()){   //If Start
                Log.d("email:", binding.getPersonalInfoEmail.text.toString())
                code = sendEmail(binding.getPersonalInfoEmail.text.toString())
                makeToast(this, CHECK_EMAIL)
                binding.getPersonalInfoVerificationPart.isVisible = true
            }
            else{
                if (binding.getPersonalInfoEmail.text.toString().isEmpty()
                    || binding.getPersonalInfoName.text.toString().isEmpty()){
                    makeToast(this, EMPTY_FIELD)
                }
                else if(!isValidPhone(binding.getPersonalInfoPhone.text.toString())){
                    makeToast(this, INVALID_PHONE)
                }
                else{
                    makeToast(this, INVALID_EMIAL)
                }
            }
        }

        binding.getPersonalInfoBack.setOnClickListener{
            finish()
        }

        binding.getPersonalInfoProceed.setOnClickListener{
            if (binding.getPersonalInfoVerificationCode.text.toString() == code){
                nextScreenWithoutFinishAndExtras(this, SelectPaymentMethod::class.java, LOGIN_STATUS, STATUS)
            }
            else{
                makeToast(this, INVALID_CODE)
            }
        }
    }
}