package com.example.essstore.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.makeRequest
import com.example.essstore.data.orderCheckout
import com.example.essstore.databinding.ActivitySubmitWishBinding
import com.example.essstore.userInfo.userLoginViewModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SubmitWish : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitWishBinding
    private lateinit var mUserViewModel: userLoginViewModel
    var id: Int = 0
    var token: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySubmitWishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)

        getData()
        binding.requestProductSubmit.setOnClickListener{

            lifecycleScope.launchWhenCreated {
                var response: Response<makeRequest>
                try {
                    response  = RetrofitInstance.api.makeRequest(
                        makeRequest(
                            binding.etSubmitWishScreenBrandName.text.toString(),
                            null,
                            binding.etSubmitWishScreenProductName.text.toString(),
                            id
                        ),
                        "Bearer "+token
                    )
                } catch (e: IOException){
                    Log.e(ContentValues.TAG, "IOException: You might not have internet connection! $e")
                    return@launchWhenCreated
                }catch (e: HttpException){
                    Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                    return@launchWhenCreated
                }
                if(response.isSuccessful && response.body()!=null){
                    nextScreenWithoutFinishAndExtras(
                        this@SubmitWish,
                        ProductSubmittedThankYou::class.java,
                        Common.LOGIN_STATUS,
                        LOGGED_IN
                    )
                }
                else{
                    Toast.makeText(baseContext, "not working", Toast.LENGTH_SHORT)
                }
            }
        }

        binding.submitWishScreenBack.setOnClickListener{
            finish()
        }
    }


    fun getData(){
        mUserViewModel.readAllData.observe(this, androidx.lifecycle.Observer {users->
            id = users[0].id
            token = users[0].tokens
        })
    }
}