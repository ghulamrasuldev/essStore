package com.example.essstore.data

import android.content.Intent
import android.util.Log
import com.example.essstore.databinding.HistoryCardBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.databinding.OrderDetailCardBinding
import com.example.essstore.view.HistoryOrderDetails
import com.squareup.picasso.Picasso

class OrderDetailAdapter(): RecyclerView.Adapter<OrderDetailAdapter.orderViewHolder>(){
    inner class orderViewHolder (val binding: OrderDetailCardBinding): RecyclerView.ViewHolder(binding.root)

    private val difCallBack= object : DiffUtil.ItemCallback<ProductX>(){
        override fun areItemsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, difCallBack)
    var products: List<ProductX>
        set(value) {
            differ.submitList(value)
        }
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailAdapter.orderViewHolder {
        return orderViewHolder(
            OrderDetailCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderDetailAdapter.orderViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            orderDetailsCardTitle.text = product.title
            orderDetailsCardPrice.text = "$ ${product.price}"
            orderDetailsDescription.text = product.description
            var url = "${product.img}"
            Picasso.get().load(url).fit().centerCrop().into(orderDetailsCardImage)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}