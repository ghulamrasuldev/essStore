package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.R
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.databinding.ActivityMainMenuBinding

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listeners
        binding.btnMenuBack.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.btnMenuCart.setOnClickListener{
            nextScreenWithoutFinish(this, CartScreen::class.java)
        }

        binding.btnMenuProfile.setOnClickListener{
            nextScreenWithoutFinish(this, Profile::class.java)
        }

        binding.btnMenuLatestProducts.setOnClickListener{
            nextScreenWithoutFinish(this, FavouriteProducts::class.java)
        }

        binding.btnMenuHome.setOnClickListener{
            nextScreenWithoutFinish(this, HomeScreen::class.java)
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

        }

    }
}