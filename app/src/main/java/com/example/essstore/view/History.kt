package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.common.Common
import com.example.essstore.data.HistoryAdapter
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityHistoryBinding
import retrofit2.HttpException
import java.io.IOException

class History : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private  lateinit var historyProductsAdaptor: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.historyProgressBar.isVisible = true
        setUpRecyclerView()
        Log.d("TAG", "Running Fine")
        fetchData()

        binding.historyBack.setOnClickListener{
            finish()
        }
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.historyProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api_two.getOrders()
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.historyProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.historyProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                historyProductsAdaptor.products = response.body()!!
                binding.historyProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.historyProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.historyRecyclerView.apply{
        historyProductsAdaptor = HistoryAdapter()
        adapter = historyProductsAdaptor
        layoutManager = LinearLayoutManager(this@History)
    }
}