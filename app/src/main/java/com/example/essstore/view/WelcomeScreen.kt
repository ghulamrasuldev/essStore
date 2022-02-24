package com.example.essstore.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.NOT_LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.databinding.ActivityWelcomeScreenBinding
import com.example.essstore.favourite.favouriteProductViewModel
import com.example.essstore.userInfo.userLoginViewModel

class WelcomeScreen : AppCompatActivity() {

    private lateinit var mCartViewModel: cartProductViewModel
    private lateinit var mUserViewModel: userLoginViewModel

    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)

        mUserViewModel.deleteAll()
        mCartViewModel.deleteAll()

        binding.btnWelcomeScreenLogin.setOnClickListener{
            nextScreenWithoutFinish(this , Login::class.java)
        }

        binding.btnWelcomeScreenSignup.setOnClickListener{
            nextScreenWithoutFinish(this, Signup::class.java)
        }

        binding.btnWelcomeScreenExplore.setOnClickListener{
            nextScreenWithoutFinishAndExtras(this, HomeScreen::class.java, LOGIN_STATUS, NOT_LOGGED_IN)
        }

    }




}