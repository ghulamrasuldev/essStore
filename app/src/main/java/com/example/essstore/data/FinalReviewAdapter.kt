package com.example.essstore.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.databinding.FinalReviewProductCardBinding

class FinalReviewAdapter(mCartViewModel: cartProductViewModel): RecyclerView.Adapter<FinalReviewAdapter.finalViewHolder>(){
    private var productList = emptyList<cartProduct>()
    val mCartViewmodel = mCartViewModel
    class finalViewHolder (val binding: FinalReviewProductCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalReviewAdapter.finalViewHolder {
        return finalViewHolder(
            FinalReviewProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FinalReviewAdapter.finalViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            finalReviewProductName.text = product.productName
            finalReviewProductPrice.text = "$ ${product.productPrice}"
            finalReviewProductQuantity.text = product.selectedQuantity.toString()
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