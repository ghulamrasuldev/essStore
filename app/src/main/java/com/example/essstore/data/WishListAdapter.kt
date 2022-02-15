package com.example.essstore.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.databinding.GeneralProductCardBinding
import com.example.essstore.databinding.WishListCardBinding
import okhttp3.internal.notify

class WishListAdapter : RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

    inner class WishListViewHolder (val binding: WishListCardBinding): RecyclerView.ViewHolder(binding.root)

    private val difCallBack= object : DiffUtil.ItemCallback<product>(){
        override fun areItemsTheSame(oldItem: product, newItem: product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: product, newItem: product): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, difCallBack)
    var products: List<product>
        set(value) {
            differ.submitList(value)
        }
        get() = differ.currentList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        return WishListViewHolder(
            WishListCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {

        holder.binding.apply {
            val product = products[position]
            wishListCardTitle.text = product.productName
            wishListBranName.text = product.productName
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}