package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
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
            nextScreenWithFinishAffinityAndExtras(
                this,
                HomeScreen::class.java,
                LOGIN_STATUS,
                LOGGED_IN
            )
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