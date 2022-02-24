package com.example.essstore.data

import com.example.essstore.databinding.GeneralProductCardBinding
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.databinding.FavouriteProductCardBinding
import com.example.essstore.view.FavouriteProducts

class FavouriteProductAdapter(mfavouriteProductViewModel: favouriteProductViewModel, mCartViewModel: cartProductViewModel): RecyclerView.Adapter<FavouriteProductAdapter.favouriteViewHolder>(){
    private var productList = emptyList<favouriteProduct>()
    val mFavouriteViewmodel = mfavouriteProductViewModel
    val mCartViewmodel = mCartViewModel
    class favouriteViewHolder (val binding: FavouriteProductCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteProductAdapter.favouriteViewHolder {
        return favouriteViewHolder(
            FavouriteProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteProductAdapter.favouriteViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            favouriteProductCardTitle.text = product.productName
            favouriteProductCardPrice.text = "$ ${product.productPrice}"
            favouriteProductCardDescription.text = product.productDescription
        }

        holder.binding.favouriteProductCardAddToCart.setOnClickListener{
            mCartViewmodel.addProductToCart(
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

    fun setData(favouriteProducts: List<favouriteProduct>){
        this.productList = favouriteProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}