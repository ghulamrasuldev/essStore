package com.example.essstore.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinishAffinity
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.sharedPrefFile
import com.example.essstore.databinding.ActivitySettingBinding
import com.example.essstore.userInfo.userLoginViewModel

class Setting : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var mUserViewModel: userLoginViewModel
    private lateinit var mCartViewModel: cartProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSettingScreenDeleteAccount.isVisible = false

        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)


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
            mCartViewModel.deleteAll()
            mUserViewModel.deleteAll()

            val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val sharedPref: SharedPreferences =  sharedPreferences

            sharedPref.edit().remove("id").apply()
            sharedPref.edit().remove("token").apply()

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