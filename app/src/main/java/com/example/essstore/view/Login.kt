package com.example.essstore.view

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common
import com.example.essstore.common.Common.ACCESS_TOKEN
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.NOT_LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.common.Common.nextScreenWithFinishAffinity
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.loginUser
import com.example.essstore.data.product
import com.example.essstore.data.registeredUserResponse
import com.example.essstore.databinding.ActivityLoginBinding
import com.example.essstore.userInfo.userLoginResponse
import com.example.essstore.userInfo.userLoginViewModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import android.content.SharedPreferences

import android.preference.PreferenceManager




class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var mUserViewModel: userLoginViewModel
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)

        //Listeners
        binding.btnLoginScreenLogin.setOnClickListener{
            lifecycleScope.launchWhenCreated {
                var response: Response<userLoginResponse>
                try {
                    response  = RetrofitInstance.api.loginRegisteredUser(
                        loginUser(
                            binding.etLoginScreenEmail.text.toString(),
                            binding.etLoginScreenPassword.text.toString(),
                        )
                    )
                } catch (e: IOException){
                    Log.e(ContentValues.TAG, "IOException: You might not have internet connection! $e")
                    return@launchWhenCreated
                }catch (e: HttpException){
                    Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                    return@launchWhenCreated
                }
                if(response.isSuccessful && response.body()!=null){

                    ACCESS_TOKEN = response.body()!!.tokens

                    mUserViewModel.deleteAll()

                    mUserViewModel.addUser(
                        response.body()!!
                    )

                    val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
                    val sharedPref:SharedPreferences.Editor =  sharedPreferences.edit()
                    //TOKEN
                    sharedPref.putString("token", response.body()!!.tokens)
                    sharedPref.apply()
                    //ID
                    sharedPref.putInt("id", response.body()!!.id)
                    sharedPref.apply()


                    mUserViewModel.readAllData.observe( this@Login, androidx.lifecycle.Observer {product ->
                        Log.d("Login", "$product")
                    })

                    Common.makeToast(
                        this@Login,
                        "Logged in successfully!"
                    )
                    nextScreenWithFinishAffinityAndExtras(
                        this@Login,
                        HomeScreen::class.java,
                        LOGIN_STATUS,
                        LOGGED_IN
                    )
                }
                else{
                    Toast.makeText(baseContext, "not working", Toast.LENGTH_SHORT)
                }
            }
        }


        binding.btnLoginScreenBack.setOnClickListener{
            nextScreenWithFinishAffinity(
                this,
                WelcomeScreen::class.java
            )
        }

        binding.btnLoginScreenSignup.setOnClickListener{
            nextScreenWithFinish(
                this, Signup::class.java
            )
        }

        binding.btnLoginScreenHome.setOnClickListener{
            nextScreenWithFinishAffinityAndExtras(
                this,
                HomeScreen::class.java,
                LOGIN_STATUS,
                NOT_LOGGED_IN
            )
        }
    }
}