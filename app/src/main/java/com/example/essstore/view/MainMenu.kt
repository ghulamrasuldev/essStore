package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.essstore.R
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.NOT_LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithFinishAffinity
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.databinding.ActivityMainMenuBinding
import com.example.essstore.userInfo.userLoginViewModel

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var STATUS: String
    private lateinit var mUserViewModel: userLoginViewModel
    private lateinit var mCartViewModel: cartProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()

        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)

        //Checking Login Status
        if (STATUS == NOT_LOGGED_IN){
            binding.btnMenuRequests.isVisible = false
            binding.btnMenuMakeRequest.isVisible=false
            binding.btnMenuLogout.isVisible = false
            binding.btnMenuProfile.isVisible = false
            binding.btnMenuSetting.isVisible = false
        }else{
            binding.btnMenuLogin.isVisible = false
            binding.btnMenuSignup.isVisible = false
        }

        //Listeners

        binding.btnMenuPromotions.setOnClickListener{
            nextScreenWithoutFinish(this, Promotions::class.java)
        }
        binding.btnMenuLogin.setOnClickListener{
            nextScreenWithoutFinish(this, Login::class.java)
        }

        binding.btnMenuSignup.setOnClickListener{
            nextScreenWithoutFinish(this, Signup::class.java)
        }

        binding.btnMenuBack.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.btnMenuCart.setOnClickListener{
            nextScreenWithoutFinishAndExtras(this, CartScreen::class.java, LOGIN_STATUS, STATUS)
        }

        binding.btnMenuProfile.setOnClickListener{
            nextScreenWithoutFinish(this, Profile::class.java)
        }

        binding.btnMenuFavouriteProducts.setOnClickListener{
            nextScreenWithoutFinishAndExtras(this, FavouriteProducts::class.java, LOGIN_STATUS, STATUS)
        }

        binding.btnMenuHome.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.btnMenuMakeRequest.setOnClickListener{
            nextScreenWithoutFinish(this, SubmitWish::class.java)
        }

        binding.btnMenuRequests.setOnClickListener{
            nextScreenWithoutFinish(this, WishList::class.java)
        }

        binding.btnMenuSetting.setOnClickListener{
            nextScreenWithoutFinish(this, Setting::class.java)
        }

        binding.btnMenuLogout.setOnClickListener{
            mCartViewModel.deleteAll()
            mUserViewModel.deleteAll()
            nextScreenWithFinishAffinity(
                this,
                Login::class.java
            )
        }

    }
}