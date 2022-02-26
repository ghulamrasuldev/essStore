package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.common.Common
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.data.WishListAdapter
import com.example.essstore.databinding.ActivityFavouriteProductsBinding
import com.example.essstore.databinding.ActivityWishListBinding
import com.example.essstore.userInfo.userLoginViewModel
import retrofit2.HttpException
import java.io.IOException

class WishList : AppCompatActivity() {
    private lateinit var binding: ActivityWishListBinding
    private val TAG = "Hot Products"
    private  lateinit var wishListAdapter: WishListAdapter
    private lateinit var mUserViewModel: userLoginViewModel
    var id: Int = 0
    var token: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserViewModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        getData()
        setUpRecyclerView()
        Log.d(TAG, "Running Fine")
        fetchData()

        //Listeners
        binding.btnWishListBack.setOnClickListener{
            finish()
        }
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.wishListProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getWishList("Bearer "+token)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.wishListProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response! $e")
                binding.wishListProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                wishListAdapter.products = response.body()!!
                binding.wishListProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response! Failed!")
                binding.wishListProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.wishListRecyclerView.apply{
        wishListAdapter = WishListAdapter()
        adapter = wishListAdapter
        layoutManager = LinearLayoutManager(this@WishList)
    }

    fun getData(){
        mUserViewModel.readAllData.observe(this, androidx.lifecycle.Observer {users->
            id = users[0].id
            token = users[0].tokens
        })
    }

}