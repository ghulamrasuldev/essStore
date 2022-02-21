package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
import com.example.essstore.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listeners
        binding.btnLoginScreenLogin.setOnClickListener{
            nextScreenWithFinishAffinityAndExtras(
                this,
                HomeScreen::class.java,
                LOGIN_STATUS,
                LOGGED_IN
            )
        }

        binding.btnLoginScreenBack.setOnClickListener{
            finish()
        }
    }
}