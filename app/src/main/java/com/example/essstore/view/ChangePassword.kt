package com.example.essstore.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinishAffinity
import com.example.essstore.databinding.ActivityChangePasswordBinding

class ChangePassword : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changePasswordBack.setOnClickListener{
            finish()
        }

        binding.changePasswordOKAY.setOnClickListener{
            makeToast(
                this,
                "Password Change Login again!"
            )
            nextScreenWithFinishAffinity(
                this,
                Login::class.java
            )
        }

    }
}