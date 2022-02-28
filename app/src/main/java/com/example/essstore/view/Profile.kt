package com.example.essstore.view

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.DISPLAY_TIME_LONG
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.updatedUserData
import com.example.essstore.data.user
import com.example.essstore.data.userPersonalInfo
import com.example.essstore.databinding.ActivityProfileBinding
import com.example.essstore.userInfo.userLoginViewModel
import retrofit2.HttpException
import java.io.IOException


class Profile : AppCompatActivity() {

    private lateinit var mUserViewModel: userLoginViewModel
    var id: Int = 0
    var token: String =""

    private lateinit var user: userPersonalInfo
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        getData()

        fetchData()

        //Listeners
        binding.btnProfileScreenBack.setOnClickListener{
            finish()
        }

        binding.personalProfileUpdateInfo.setOnClickListener{
            updateData()
        }

    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.profileScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api_two.getUserInfo()
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

                user = response.body()!!
                //Listeners
                binding.etProfileScreenName.setText(response.body()!!.name)
                binding.etProfileScreenEmail.setText(response.body()!!.email)
                binding.etProfileScreenPhone.setText(response.body()!!.contactNo.toString())
                binding.etProfileScreenGender.setText(response.body()!!.gender.toString())
                binding.etProfileScreenUserName.setText(response.body()!!.username)
                binding.profileScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.profileScreenProgressBar.isVisible = false

            }
        }
    }

    private fun updateData() {
        val user = updatedUserData(
            binding.etProfileScreenPhone.text.toString(),
            binding.etProfileScreenEmail.text.toString(),
            binding.etProfileScreenGender.text.toString(),
            user.image,
            binding.etProfileScreenName.text.toString(),
            binding.etProfileScreenUserName.text.toString()
        )
        Log.d("Updated Data: ", "$user")
        lifecycleScope.launchWhenCreated {
            binding.profileScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api_two.updateUserInfo(
                    updatedUserData(
                        binding.etProfileScreenPhone.text.toString(),
                        binding.etProfileScreenEmail.text.toString(),
                        binding.etProfileScreenGender.text.toString(),
                        user.image,
                        binding.etProfileScreenName.text.toString(),
                        binding.etProfileScreenUserName.text.toString()
                    )
                )
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
                //Listeners
                binding.etProfileScreenName.setText(response.body()!!.name)
                binding.etProfileScreenEmail.setText(response.body()!!.email)
                binding.etProfileScreenPhone.setText(response.body()!!.contactNo)
                binding.etProfileScreenGender.setText(response.body()!!.gender)
                binding.etProfileScreenUserName.setText(response.body()!!.username)
                binding.profileScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")

            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.profileScreenProgressBar.isVisible = false

            }
        }
    }

    fun getData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            Common.sharedPrefFile,
            Context.MODE_PRIVATE)
        val sharedPref: SharedPreferences =  sharedPreferences
        id = sharedPref.getInt("id", -1)
        token = sharedPref.getString("token", "").toString()
    }
}