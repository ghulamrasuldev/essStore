package com.example.essstore.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.GeneralProductCardBinding

class SimpleProductsAdapter(mCartViewModel: cartProductViewModel) : RecyclerView.Adapter<SimpleProductsAdapter.ProductViewHolder>() {

    private var mCartViewModel: cartProductViewModel = mCartViewModel

    inner class ProductViewHolder (val binding: GeneralProductCardBinding): RecyclerView.ViewHolder(binding.root)

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
            GeneralProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            generalProductCardTitle.text = product.productName
            generalProductCardDescription.text = product.productDescription
            generalProductCardPrice.text = "$ ${product.productPrice}"
        }

        holder.binding.generalProductCardAddToCart.setOnClickListener{
            makeToast(
                holder.itemView.context,
                "Product added to cart!"
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
        return products.size
    }
}