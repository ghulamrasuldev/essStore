package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.registerUser
import com.example.essstore.databinding.ActivitySignupBinding
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Signup", "Running Fine")

        binding.btnSignupScreenSignup.setOnClickListener{
            lifecycleScope.launchWhenCreated {
                RetrofitInstance.api.registerUser(
                    registerUser(
                        binding.etProfileScreenEmail.text.toString(),
                        binding.etProfileScreenUserName.text.toString(),
                        binding.etProfileScreenPassword.text.toString(),
                    )
                )
                nextScreenWithoutFinish(baseContext, HomeScreen::class.java)
            }
            makeToast(
                this,
                "Signed up successfully!"
            )
        }

        binding.btnSignupScreenBack.setOnClickListener{
            finish()
        }
    }
}