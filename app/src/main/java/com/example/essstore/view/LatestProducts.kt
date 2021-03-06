package com.example.essstore.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common
import com.example.essstore.common.Common.API_KEY
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityLatestProductsBinding
import com.example.essstore.favourite.favouriteProductViewModel
import retrofit2.HttpException
import java.io.IOException

class LatestProducts : AppCompatActivity() {
    val TAG = "Latest Prodcts"
    private lateinit var STATUS: String
    lateinit var mCartViewModel: cartProductViewModel
    lateinit var mfavouriteproductViewModel: favouriteProductViewModel
    private lateinit var binding: ActivityLatestProductsBinding
    private  lateinit var productAdapter: SimpleProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLatestProductsBinding.inflate(layoutInflater)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        setContentView(binding.root)
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        mfavouriteproductViewModel = ViewModelProvider(this).get(favouriteProductViewModel::class.java)
        setUpRecyclerView()
        Log.d(TAG, "Running Fine")
        fetchData()

        //Listeners
        binding.latestProductsScreenBack.setOnClickListener{
            finish()
        }

        binding.btnLatestProductsCart.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                CartScreen::class.java,
                LOGIN_STATUS,
                STATUS
            )
        }

    }
    //"57b501f0"
    fun fetchData(){
        lifecycleScope.launchWhenCreated {
            binding.latestProductsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getNewArrivals(API_KEY)
            } catch (e: IOException){
                Log.e(ContentValues.TAG, "IOException: You might not have internet connection!")
                binding.latestProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.latestProductsScreenProgressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                productAdapter.products = response.body()!!
                binding.latestProductsScreenProgressBar.isVisible = false
                Log.d(ContentValues.TAG, "${response.raw().request.url}")
            }
            else{
                Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                binding.latestProductsScreenProgressBar.isVisible = false

            }
        }
    }

    private fun setUpRecyclerView() = binding.latestProductsScreenRecyclerView.apply{
        productAdapter = SimpleProductsAdapter(mCartViewModel, mfavouriteproductViewModel)
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@LatestProducts)
    }

    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }, Common.DISPLAY_TIME_TOO_LONG)
    }
}