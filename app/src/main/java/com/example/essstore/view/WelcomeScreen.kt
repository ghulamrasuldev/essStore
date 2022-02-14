package com.example.essstore.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWelcomeScreenLogin.setOnClickListener{
            nextScreenWithoutFinish(this , Login::class.java)
        }

        binding.btnWelcomeScreenSignup.setOnClickListener{
            nextScreenWithoutFinish(this, Signup::class.java)
        }

        binding.btnWelcomeScreenExplore.setOnClickListener{
            nextScreenWithFinish(this, HomeScreen::class.java)
        }

    }


}