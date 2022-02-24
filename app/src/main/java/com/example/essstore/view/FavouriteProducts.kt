package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.FavouriteProductAdapter
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityFavouriteProductsBinding
import com.example.essstore.favourite.favouriteProductViewModel
import retrofit2.HttpException
import java.io.IOException

class FavouriteProducts : AppCompatActivity() {
    private val TAG = "Hot Products"
    private lateinit var STATUS: String
    lateinit var mFavouriteProductViewModel: favouriteProductViewModel
    lateinit var mCartViewModel: cartProductViewModel
    private lateinit var binding: ActivityFavouriteProductsBinding
    private  lateinit var productAdapter: FavouriteProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteProductsBinding.inflate(layoutInflater)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        mFavouriteProductViewModel = ViewModelProvider(this).get(favouriteProductViewModel::class.java)
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)

        setContentView(binding.root)
        setUpRecyclerView()
        Log.d(TAG, "Running Fine")
        mFavouriteProductViewModel.readAllData.observe(this, androidx.lifecycle.Observer { product ->
            productAdapter.setData(product)
        })

        //Listeners
        binding.favouriteScreenBack.setOnClickListener{
            finish()
        }

        binding.btnFavouriteProductsCart.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                CartScreen::class.java,
                LOGIN_STATUS,
                STATUS
            )
        }
    }

    private fun setUpRecyclerView() = binding.favouriteProductsScreenRecyclerView.apply{
        productAdapter = FavouriteProductAdapter(mFavouriteProductViewModel, mCartViewModel)
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@FavouriteProducts)
    }
}