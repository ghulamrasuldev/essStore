package com.example.essstore.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.R
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.API_KEY
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.NOT_LOGGED_IN
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.BoxProductsAdapter
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.product
import com.example.essstore.databinding.ActivityHomeScreenBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class HomeScreen : AppCompatActivity() {
    val TAG = "Home Screen"
    lateinit var STATUS: String
    lateinit var mCartViewModel: cartProductViewModel
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var productAdapterLatest: BoxProductsAdapter
    private lateinit var productAdapterHot: BoxProductsAdapter
    private lateinit var productAdapterPromotions: BoxProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        setContentView(binding.root)
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        setUpRecyclerViewLatest()
        setUpRecyclerViewHot()
        setUpRecyclerViewPromotions()
        Log.d(TAG, "Running Fine")
        fetchData()
        //Listeners
        binding.homeScreenLatestProductsViewAll.setOnClickListener{
            nextScreenWithoutFinish(this, LatestProducts::class.java)
        }

        binding.homeScreenHotProductsViewAll.setOnClickListener{
            nextScreenWithoutFinish(this, HotProducts::class.java)
        }

        binding.homeScreenPromotionsViewAll.setOnClickListener{
            nextScreenWithoutFinish(this, Promotions::class.java)
        }

        binding.btnHomeScreenCart.setOnClickListener{
            nextScreenWithoutFinish(this, CartScreen::class.java)
        }

        binding.btnHomeScreenMenu.setOnClickListener{
            nextScreenWithoutFinishAndExtras(this, MainMenu::class.java, LOGIN_STATUS, STATUS)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }
//"57b501f0"
    private fun fetchData(){
        lifecycleScope.launchWhenCreated {
            val latest: Response<List<product>>
            val hot: Response<List<product>>
            val promotion: Response<List<product>>
            binding.homeScreenProgressBar.isVisible = true
            try {
                latest = RetrofitInstance.api.getNewArrivals(API_KEY)
                hot = RetrofitInstance.api.getHotProducts(API_KEY)
                promotion =RetrofitInstance.api.getPromotions(API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection! $e")
                binding.homeScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.homeScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(latest.isSuccessful && latest.body()!=null && hot.isSuccessful && hot.body()!=null && promotion.isSuccessful && promotion.body()!=null){
                binding.homeScreenProgressBar.isVisible = false
                binding.homeScreenLatestProductsLinearLayout.isVisible = true
                binding.homeScreenHotProductsLinearLayout.isVisible = true
                binding.homeScreenPromotionsLinearLayout.isVisible = true
                productAdapterPromotions.products = promotion.body()!!
                productAdapterLatest.products = latest.body()!!
                productAdapterHot.products = hot.body()!!
                Log.d(ContentValues.TAG, "${latest.raw().request.url}")
                Log.d(ContentValues.TAG, "${hot.raw().request.url}")
                Log.d(ContentValues.TAG, "${promotion.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.homeScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerViewLatest() = binding.homeScreenRecyclerviewLatest.apply{
        productAdapterLatest = BoxProductsAdapter(mCartViewModel)
        adapter = productAdapterLatest
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

    private fun setUpRecyclerViewHot() = binding.homeScreenRecyclerviewHot.apply{
        productAdapterHot = BoxProductsAdapter(mCartViewModel)
        adapter = productAdapterHot
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

    private fun setUpRecyclerViewPromotions() = binding.homeScreenRecyclerviewPromotions.apply{
        productAdapterPromotions = BoxProductsAdapter(mCartViewModel)
        adapter = productAdapterPromotions
        layoutManager = LinearLayoutManager(this@HomeScreen, RecyclerView.HORIZONTAL, false)
    }

}