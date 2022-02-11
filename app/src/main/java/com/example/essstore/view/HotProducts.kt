package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityHotProductsBinding
import retrofit2.HttpException
import java.io.IOException

class HotProducts : AppCompatActivity() {
    private val TAG = "Hot Products"
    private  lateinit var productAdapter: SimpleProductsAdapter
    private lateinit var binding: ActivityHotProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.hotProductsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException){
                binding.hotProductsScreenProgressBar.isVisible = false
                Log.e(TAG, "IOException: You might not have internet connection!")
                return@launchWhenCreated
            }catch (e: HttpException){
                binding.hotProductsScreenProgressBar.isVisible = false
                Log.e(TAG, "IOException: Unexpected Response!")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapter.products = response.body()!!
            }
            else{
                Log.e(TAG, "IOException: Unexpected Response!")
            }
            binding.hotProductsScreenProgressBar.isVisible = false
        }
    }

    private fun setUpRecyclerView() = binding.hotProductsScreenRecyclerView.apply{
        productAdapter = SimpleProductsAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@HotProducts)

    }
}