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
import com.example.essstore.common.Common.API_KEY
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.nextScreenWithoutFinish
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.RetrofitInstance
import com.example.essstore.data.SimpleProductsAdapter
import com.example.essstore.databinding.ActivityPromotionsBinding
import com.example.essstore.favourite.favouriteProductViewModel
import retrofit2.HttpException
import java.io.IOException

class Promotions : AppCompatActivity() {
    private lateinit var STATUS: String
    lateinit var mCartViewModel: cartProductViewModel
    lateinit var mfavouriteproductViewModel: favouriteProductViewModel
    private lateinit var productAdapter: SimpleProductsAdapter
    private lateinit var binding: ActivityPromotionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromotionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        mfavouriteproductViewModel = ViewModelProvider(this).get(favouriteProductViewModel::class.java)

        setUpRecyclerView()
        fetchData()

        // Listeners
        binding.btnPromotionsScreenBack.setOnClickListener{
            finish()
        }

        binding.btnPromotionsCart.setOnClickListener{
            nextScreenWithoutFinishAndExtras(
                this,
                CartScreen::class.java,
                LOGIN_STATUS,
                STATUS
            )
        }
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            binding.promotionsScreenProgressBar.isVisible = true
            val response= try {
                RetrofitInstance.api.getPromotions(API_KEY)
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
        productAdapter = SimpleProductsAdapter(mCartViewModel,mfavouriteproductViewModel)
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@Promotions)
    }
}