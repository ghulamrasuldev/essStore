package com.example.essstore.data

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common
import com.example.essstore.common.Common.PRODUCT_ADDED_TO_CART
import com.example.essstore.common.Common.PRODUCT_ADDED_TO_FAVOURITE
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.GeneralProductCardBinding
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductViewModel
import com.squareup.picasso.Picasso

class SimpleProductsAdapter(mCartViewModel: cartProductViewModel, mfavouriteProductViewModel: favouriteProductViewModel) : RecyclerView.Adapter<SimpleProductsAdapter.ProductViewHolder>() {

    private var mCartViewModel: cartProductViewModel = mCartViewModel
    private var mfavouriteProductViewModel: favouriteProductViewModel = mfavouriteProductViewModel

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
            var url = "${product.img}"
//            url = "https://www.lays.com/sites/lays.com/files/2021-07/XL%20Lay%27s%20Flamin%27%20Hot.png"
            Log.d("Box","$url")
            Picasso.get().load(url).fit().centerCrop().into(generalProductCardImage)
        }

        holder.binding.generalProductCardAddToCart.setOnClickListener{
            makeToast(
                holder.itemView.context,
                PRODUCT_ADDED_TO_CART
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
                    selectedQuantity = 1,
                    product.img
                )
            )
        }

        holder.binding.generalProductCardFavourite.setOnClickListener{
            mfavouriteProductViewModel.addProductToFavourite(
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
                    selectedQuantity = 1,
                    product.img
                )
            )
            makeToast(holder.itemView.context, PRODUCT_ADDED_TO_FAVOURITE)


        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}