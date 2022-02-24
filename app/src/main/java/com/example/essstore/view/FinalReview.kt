package com.example.essstore.view

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityFinalReviewBinding
import android.app.PendingIntent

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.content.ContentValues
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.LOGIN_STATUS
import com.example.essstore.common.Common.PAYMENT_METHOD
import com.example.essstore.common.Common.nextScreenWithoutFinishAndExtras
import com.example.essstore.data.*
import com.example.essstore.userInfo.userLoginViewModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class FinalReview : AppCompatActivity() {
    private lateinit var STATUS: String
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    var channelID = "com.example.essstore"
    var description: String = "Order Confirmed"
    private lateinit var finalAdapter: FinalReviewAdapter
    private lateinit var binding: ActivityFinalReviewBinding
    lateinit var mCartViewModel: cartProductViewModel
    lateinit var mUserLoginModel: userLoginViewModel
    var list: ArrayList<Int> = arrayListOf<Int>()
    var id: Int = 0
    var token: String =""
    var products: List<cartProduct>? = null
    var total = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val paymentMethod = intent.getStringExtra(PAYMENT_METHOD)
        STATUS = intent.getStringExtra(LOGIN_STATUS).toString()
        binding.finalReviewScreenPaymentMethod.text = paymentMethod
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        mUserLoginModel = ViewModelProvider(this).get(userLoginViewModel::class.java)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        setUpRecyclerView()
        getData()

        mCartViewModel.readAllData.observe(this, androidx.lifecycle.Observer { product ->
            finalAdapter.setData(product)
            products = product
            total = getTotal()
            binding.finalReviewTotalAmount.text = "$ ${total}"
        })

        //Listeners
        binding.finalReviewBack.setOnClickListener{
            finish()
        }

        binding.finalReviewCreateOrder.setOnClickListener{
            Log.d("Token: ", token)
            Log.d("payment method: ", paymentMethod.toString())


            lifecycleScope.launchWhenCreated {
                var response: Response<orderCheckout>
                try {
                    response  = RetrofitInstance.api.checkout(
                        orderCheckout(paymentMethod.toString(), list, id), "Bearer "+token
                    )
                } catch (e: IOException){
                    Log.e(ContentValues.TAG, "IOException: You might not have internet connection! $e")
                    return@launchWhenCreated
                }catch (e: HttpException){
                    Log.e(ContentValues.TAG, "IOException: Unexpected Response!")
                    return@launchWhenCreated
                }
                if(response.isSuccessful && response.body()!=null){
                    createNotification()
                    nextScreenWithoutFinishAndExtras(
                        this@FinalReview,
                        OrderCreatedThankYou::class.java,
                        LOGIN_STATUS,
                        STATUS
                    )
                }
                else{
                    Toast.makeText(baseContext, "not working", Toast.LENGTH_SHORT)
                }
            }
        }

    }

    private fun createNotification() {

        val intent = Intent(this, HomeScreen::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelID, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelID)
                .setContentTitle("Order Confirmed")
                .setContentText("Your Order was Confirmed.")
                .setSmallIcon(R.drawable.sym_contact_card)
                .setContentIntent(pendingIntent)
        }
        else{
            builder = Notification.Builder(this)
                .setContentTitle("Order Confirmed")
                .setContentText("Your Order was Confirmed.")
                .setSmallIcon(R.drawable.sym_contact_card)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }

    private fun setUpRecyclerView() = binding.finalReviewRecyclerView.apply{
        finalAdapter = FinalReviewAdapter(mCartViewModel)
        adapter = finalAdapter
        layoutManager = LinearLayoutManager(this@FinalReview)
    }

    @JvmName("getTotal1")
    private fun getTotal() : Int {
        var total = 0
        for (product in products!!){
            total += product.productPrice*product.selectedQuantity
        }
        return total
    }

    fun getData(){

        mCartViewModel.readAllData.observe(this, androidx.lifecycle.Observer { products->
            for (product in products){
                list.add(product.id)
            }
        })

        mUserLoginModel.readAllData.observe(this, androidx.lifecycle.Observer {users->
            id = users[0].id
            token = users[0].tokens
        })
    }

}