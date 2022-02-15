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
import com.example.essstore.databinding.ActivityFavouriteProductsBinding
import retrofit2.HttpException
import java.io.IOException

class FavouriteProducts : AppCompatActivity() {
    private val TAG = "Hot Products"
    private lateinit var binding: ActivityFavouriteProductsBinding
    private  lateinit var productAdapter: SimpleProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        Log.d(TAG, "Running Fine")
        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.favouriteProductsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getTodos(Common.API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.favouriteProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.favouriteProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapter.products = response.body()!!
                binding.favouriteProductsScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.favouriteProductsScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.favouriteProductsScreenRecyclerView.apply{
        productAdapter = SimpleProductsAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@FavouriteProducts)
    }
}