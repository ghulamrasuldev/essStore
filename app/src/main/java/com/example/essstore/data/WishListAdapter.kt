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

    private val difCallBack= object : DiffUtil.ItemCallback<wishListResponse>(){
        override fun areItemsTheSame(oldItem: wishListResponse, newItem: wishListResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: wishListResponse, newItem: wishListResponse): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, difCallBack)
    var products: List<wishListResponse>
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
            wishListCardTitle.text = product.title
            wishListBranName.text = product.brandName
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}