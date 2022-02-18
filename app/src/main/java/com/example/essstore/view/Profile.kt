package com.example.essstore.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.DISPLAY_TIME_LONG
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.user
import com.example.essstore.databinding.ActivityProfileBinding
import retrofit2.HttpException
import java.io.IOException


class Profile : AppCompatActivity() {

    private var user: List<user>? = null
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()

        //Listeners
        binding.btnProfileScreenBack.setOnClickListener{
            finish()
        }

        binding.personalProfileUpdateInfo.setOnClickListener{
            makeToast(
                this,
                "Profile Information Updated!"
            )
            finish()
        }

    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.profileScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getUser(Common.API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "$e")
                binding.profileScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.profileScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                user = listOf(response.body()!!)

                //Listeners
                binding.etProfileScreenName.setText(user!![0].name)
                binding.etProfileScreenEmail.setText(user!![0].email)
                binding.etProfileScreenPhone.setText(user!![0].phone)
                binding.etProfileScreenGender.setText(user!![0].gender)
                binding.etProfileScreenUserName.setText(user!![0].username)
                binding.profileScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")

            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.profileScreenProgressBar.isVisible = false

            }
        }
    }
}