package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.data.CartAdapter
import com.example.essstore.databinding.ActivityCartScreenBinding

class CartScreen : AppCompatActivity() {
    private lateinit var cartAdapter: CartAdapter
    private lateinit var binding: ActivityCartScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        setUpRecyclerView()
        mCartViewModel.readAllData.observe(this, androidx.lifecycle.Observer { product ->
            cartAdapter.setData(product)
        })
    }

    private fun setUpRecyclerView() = binding.cartScreenRecyclerView.apply{
        cartAdapter = CartAdapter()
        adapter = cartAdapter
        layoutManager = LinearLayoutManager(this@CartScreen)
    }
}