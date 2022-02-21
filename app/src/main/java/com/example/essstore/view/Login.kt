package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithFinishAffinityAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.loginUser
import com.example.essstore.data.registeredUserResponse
import com.example.essstore.databinding.ActivityLoginBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listeners
        binding.btnLoginScreenLogin.setOnClickListener{
            lifecycleScope.launchWhenCreated {
                var response: Response<registeredUserResponse>
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

            Common.makeToast(
                this,
                "Signed up successfully!"
            )
        }


        binding.btnLoginScreenBack.setOnClickListener{
            finish()
        }
    }
}