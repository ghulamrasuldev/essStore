package com.example.essstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.NOT_LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.CartAdapter
import com.example.essstore.databinding.ActivityCartScreenBinding

class CartScreen : AppCompatActivity() {
    private lateinit var STATUS: String
    private lateinit var cartAdapter: CartAdapter
    private lateinit var binding: ActivityCartScreenBinding
    lateinit var mCartViewModel: cartProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        setUpRecyclerView()
        mCartViewModel.readAllData.observe(this, androidx.lifecycle.Observer { product ->
            cartAdapter.setData(product)
            binding.cartScreenNoProductInCart.isVisible = cartAdapter.productList.isEmpty()
        })
        binding.cartScreenBack.setOnClickListener{
            finish()
        }
        binding.cartScreenProceed.setOnClickListener{

            if (STATUS == NOT_LOGGED_IN){
                nextScreenWithoutFinishAndExtras(
                    this,
                    GetPersonalInfo::class.java,
                    LOGIN_STATUS,
                    STATUS
                )
            }
            else{
                nextScreenWithoutFinishAndExtras(
                    this,
                    SelectPaymentMethod::class.java,
                    LOGIN_STATUS,
                    STATUS
                )
            }
        }
    }

    private fun setUpRecyclerView() = binding.cartScreenRecyclerView.apply{
        cartAdapter = CartAdapter(mCartViewModel)
        adapter = cartAdapter
        layoutManager = LinearLayoutManager(this@CartScreen)
    }
}