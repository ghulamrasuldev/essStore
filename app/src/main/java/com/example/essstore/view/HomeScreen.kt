package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.common.Common
import com.example.essstore.data.BoxProductsAdapter
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityHomeScreenBinding
import retrofit2.HttpException
import java.io.IOException

class HomeScreen : AppCompatActivity() {
    val TAG = "Home Screen"
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var productAdapterLatest: BoxProductsAdapter
    private lateinit var productAdapterHot: BoxProductsAdapter
    private lateinit var productAdapterPromotions: BoxProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerViewLatest()
        setUpRecyclerViewHot()
        setUpRecyclerViewPromotions()
        Log.d(TAG, "Running Fine")
        lifecycleScope.launchWhenCreated {
            binding.homeScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getTodos("57b501f0")
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.homeScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.homeScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapterPromotions.products = response.body()!!
                productAdapterLatest.products = response.body()!!
                productAdapterHot.products = response.body()!!
                binding.homeScreenProgressBar.isVisible = false

                Log.d(ContentValues.TAG, "${Common.list}")
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.homeScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerViewLatest() = binding.homeScreenRecyclerviewLatest.apply{
        productAdapterLatest = BoxProductsAdapter()
        adapter = productAdapterLatest
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

    private fun setUpRecyclerViewHot() = binding.homeScreenRecyclerviewHot.apply{
        productAdapterHot = BoxProductsAdapter()
        adapter = productAdapterHot
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

    private fun setUpRecyclerViewPromotions() = binding.homeScreenRecyclerviewPromotions.apply{
        productAdapterPromotions = BoxProductsAdapter()
        adapter = productAdapterPromotions
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

}