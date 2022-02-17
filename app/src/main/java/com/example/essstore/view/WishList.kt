package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.common.Common
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.data.WishListAdapter
import com.example.essstore.databinding.ActivityFavouriteProductsBinding
import com.example.essstore.databinding.ActivityWishListBinding
import retrofit2.HttpException
import java.io.IOException

class WishList : AppCompatActivity() {
    private lateinit var binding: ActivityWishListBinding
    private val TAG = "Hot Products"
    private  lateinit var wishListAdapter: WishListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishListBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                RetrofitInstance.api.getTodos(Common.API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.wishListProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.wishListProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                wishListAdapter.products = response.body()!!
                binding.wishListProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.wishListProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.wishListRecyclerView.apply{
        wishListAdapter = WishListAdapter()
        adapter = wishListAdapter
        layoutManager = LinearLayoutManager(this@WishList)
    }
}