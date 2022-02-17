package com.example.essstore.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.BoxProductCardBinding

class BoxProductsAdapter (mCartViewModel: cartProductViewModel) : RecyclerView.Adapter<BoxProductsAdapter.ProductViewHolder>() {


    private var limit = 7
    private var mCartViewModel: cartProductViewModel = mCartViewModel

    inner class ProductViewHolder (val binding: BoxProductCardBinding): RecyclerView.ViewHolder(binding.root)

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            BoxProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            boxProductCardTitle.text = product.productName
            boxProductCardDescription.text = product.productDescription
            boxProductCardPrice.text = "$ ${product.productPrice}"
        }

        holder.binding.boxProductCardAddToCart.setOnClickListener{
            makeToast(
                holder.itemView.context,
                "Product added to Cart!"
            )
            mCartViewModel.addProductToCart(
                cartProduct(
                    product.id,
                    product.availableQuantity,
                    product.category,
                    product.dateCreated,
                    product.discount,
                    product.productDescription,
                    product.productImage,
                    product.productName,
                    product.productPrice,
                    product.soldQuantity,
                    selectedQuantity = 1
                )
            )
        }
    }

    override fun getItemCount(): Int {
        if (products.size>7){
            return limit
        }
        return products.size
    }
}