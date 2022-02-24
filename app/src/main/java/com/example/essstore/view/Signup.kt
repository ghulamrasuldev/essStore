package com.example.essstore.view

import android.app.Activity
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.makeToast
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.registerUser
import com.example.essstore.data.registeredUserResponse
import com.example.essstore.databinding.ActivitySignupBinding
import com.example.essstore.userInfo.userLoginResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(
            "Signup",
            "Running Fine"
        )

        binding.btnSignupScreenSignup.setOnClickListener{
             lifecycleScope.launchWhenCreated {
                    var response: Response<userLoginResponse>
                    try {
                        response  = RetrofitInstance.api.registerUser(
                            registerUser(
                                binding.etProfileScreenEmail.text.toString(),
                                binding.etProfileScreenUserName.text.toString(),
                                binding.etProfileScreenPassword.text.toString(),
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
                        nextScreenWithFinishAffinityAndExtras(
                            this@Signup,
                            HomeScreen::class.java,
                            LOGIN_STATUS,
                            LOGGED_IN
                        )
                    }
                    else{
                        Toast.makeText(baseContext, "not working", Toast.LENGTH_SHORT)
                    }
                }



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