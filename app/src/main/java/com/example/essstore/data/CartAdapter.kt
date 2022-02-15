package com.example.essstore.data

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.databinding.CartProductCardBinding

class CartAdapter: RecyclerView.Adapter<CartAdapter.cartViewHolder>(){
    private var productList = emptyList<cartProduct>()
    class cartViewHolder (val binding: CartProductCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.cartViewHolder {
        return cartViewHolder(
            CartProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartAdapter.cartViewHolder, position: Int) {

        holder.binding.apply {
            val product = productList[position]
            cartProductTitle.text = product.productName
            cartProductPrice.text = "$ ${product.productPrice}"
            cartProductDescription.text = product.productDescription
            cartProductSelectedQuantity.text = product.availableQuantity.toString()

        }
    }

    fun setData(cartProducts: List<cartProduct>){
        this.productList = cartProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}