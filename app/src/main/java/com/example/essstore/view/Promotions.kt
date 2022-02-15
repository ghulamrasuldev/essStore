package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.common.Common.API_KEY
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityPromotionsBinding
import retrofit2.HttpException
import java.io.IOException

class Promotions : AppCompatActivity() {
    private lateinit var productAdapter: SimpleProductsAdapter
    private lateinit var binding: ActivityPromotionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromotionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        fetchData()
        binding.btnPromotionsScreenBack.setOnClickListener{
            finish()
        }

    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.promotionsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getTodos(API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "$e")
                binding.promotionsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.promotionsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapter.products = response.body()!!
                binding.promotionsScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.promotionsScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.promotionsScreenRecyclerView.apply{
        productAdapter = SimpleProductsAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@Promotions)
    }
}