package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.registerUser
import com.example.essstore.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Signup", "Running Fine")

        binding.btnSignupScreenSignup.setOnClickListener{

            if (binding.etProfileScreenPassword.text == binding.etProfileScreenVerifyPassword){
                RetrofitInstance.api.registerUser(
                    registerUser(
                        binding.etProfileScreenEmail.text.toString(),
                        binding.etProfileScreenUserName.text.toString(),
                        binding.etProfileScreenPassword.text.toString(),
                    )
                )
            }
            makeToast(
                this,
                "Signed up successfully!"
            )
            nextScreenWithFinish(this, HomeScreen::class.java)
        }

        binding.btnSignupScreenBack.setOnClickListener{
            finish()
        }
    }
}