package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.data.HistoryAdapter
import com.example.essstore.data.OrderDetailAdapter
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.databinding.ActivityHistoryOrderDetailsBinding
import retrofit2.HttpException
import java.io.IOException
import kotlin.properties.Delegates

class HistoryOrderDetails : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryOrderDetailsBinding
    private  lateinit var orderDetailsAdaptor: OrderDetailAdapter
    private var id by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("id", -1)
        binding.orderDetailsProgressBar.isVisible = true
        setUpRecyclerView()
        Log.d("TAG", "Running Fine")
        fetchData()


        binding.orderDatialsBack.setOnClickListener{
            finish()
        }

    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.orderDetailsProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api_two.getOrders()
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.orderDetailsProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.orderDetailsProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){

                for (order in response.body()!!){
                    if (order.id == id){
                        orderDetailsAdaptor.products = order.products
                    }
                }
//                orderDetailsAdaptor.products = response.body()!!
                binding.orderDetailsProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.orderDetailsProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.orderDetailsRecyclerView.apply{
        orderDetailsAdaptor = OrderDetailAdapter()
        adapter = orderDetailsAdaptor
        layoutManager = LinearLayoutManager(this@HistoryOrderDetails)
    }

}