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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.common.Common
import com.example.essstore.common.Common.API_KEY
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityHotProductsBinding
import retrofit2.HttpException
import java.io.IOException

class HotProducts : AppCompatActivity() {
    private val TAG = "Hot Products"
    private lateinit var binding: ActivityHotProductsBinding
    private  lateinit var productAdapter: SimpleProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        Log.d(TAG, "Running Fine")
        fetchData()
        binding.btnHotProductsScreenBack.setOnClickListener{
            finish()
        }

    }
//"57b501f0"
    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.hotProductsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getTodos(API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.hotProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.hotProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapter.products = response.body()!!
                binding.hotProductsScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.hotProductsScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.hotProductsScreenRecyclerView.apply{
        productAdapter = SimpleProductsAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@HotProducts)
    }
}