package com.example.essstore.data

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.BASE_URL
import com.example.essstore.common.Common.BASE_URL_TWO
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.BoxProductCardBinding
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductViewModel
import com.squareup.picasso.Picasso

class BoxProductsAdapter (mCartViewModel: cartProductViewModel, mfavouriteViewModel: favouriteProductViewModel) : RecyclerView.Adapter<BoxProductsAdapter.ProductViewHolder>() {
    private var limit = 10
    private var mCartViewModel: cartProductViewModel = mCartViewModel
    private var mfavouriteViewModel: favouriteProductViewModel = mfavouriteViewModel
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
        Log.d("product", "${product.productImage}")
        holder.binding.apply {
            boxProductCardTitle.text = product.productName
            boxProductCardDescription.text = product.productDescription
            boxProductCardPrice.text = "$ ${product.productPrice}"
            var url = "${product.productImage}"
//            url = "https://www.lays.com/sites/lays.com/files/2021-07/XL%20Lay%27s%20Flamin%27%20Hot.png"
            Log.d("Box","$url")
            Picasso.get().load(url).fit().centerCrop().into(boxProductCardImage)
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

        holder.binding.boxProductCardFavourite.setOnClickListener{
            mfavouriteViewModel.addProductToFavourite(
                favouriteProduct(
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