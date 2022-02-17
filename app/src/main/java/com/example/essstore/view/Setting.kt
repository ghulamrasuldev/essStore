package com.example.essstore.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.essstore.common.Common
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinishAffinity
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.databinding.ActivitySettingBinding

class Setting : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Listeners
        binding.btnSettingScreenBack.setOnClickListener{
            finish()
        }

        binding.btnSettingScreenProfile.setOnClickListener{
            nextScreenWithoutFinish(
                this,
                Profile::class.java
            )
        }

        binding.btnSettingScreenChangePassword.setOnClickListener{
            nextScreenWithoutFinish(
                this,
                ChangePassword::class.java
            )
        }

        binding.btnSettingScreenLogout.setOnClickListener{
            makeToast(
                this,
                "Logged Out!"
            )
            nextScreenWithFinishAffinity(
                this,
                Login::class.java
            )
        }

        binding.btnSettingScreenDeleteAccount.setOnClickListener{
            Toast.makeText(this, "Account deleted, create new one!", Toast.LENGTH_SHORT)

            nextScreenWithFinishAffinity(
                this,
                Signup::class.java
            )
        }
    }
}