package com.example.essstore.view

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.essstore.databinding.ActivityFinalReviewBinding
import android.app.PendingIntent

import androidx.core.app.NotificationCompat

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common
import com.example.essstore.common.Common.PAYMENT_METHOD
import com.example.essstore.common.Common.nextScreenWithFinish
import com.example.essstore.data.CartAdapter
import com.example.essstore.data.FinalReviewAdapter


class FinalReview : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    var channelID = "com.example.essstore"
    var description: String = "Order Confirmed"
    private lateinit var finalAdapter: FinalReviewAdapter
    private lateinit var binding: ActivityFinalReviewBinding
    lateinit var mCartViewModel: cartProductViewModel
    var products: List<cartProduct>? = null
    var total = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val paymentMethod = intent.getStringExtra(PAYMENT_METHOD)
        binding.finalReviewScreenPaymentMethod.text = paymentMethod
        mCartViewModel = ViewModelProvider(this).get(cartProductViewModel::class.java)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        setUpRecyclerView()

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

            createNotification()

            nextScreenWithFinish(
                this,
                OrderCreatedThankYou::class.java
            )
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

}