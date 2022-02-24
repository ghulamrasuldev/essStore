package com.example.essstore.data

import com.example.essstore.databinding.GeneralProductCardBinding
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FavouriteProductAdapter(mfavouriteProductViewModel: favouriteProductViewModel): RecyclerView.Adapter<FavouriteProductAdapter.favouriteViewHolder>(){
    private var productList = emptyList<favouriteProduct>()
    val mCartViewmodel = mfavouriteProductViewModel
    class favouriteViewHolder (val binding: GeneralProductCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteProductAdapter.favouriteViewHolder {
        return favouriteViewHolder(
            GeneralProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteProductAdapter.favouriteViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            generalProductCardTitle.text = product.productName
            generalProductCardPrice.text = "$ ${product.productPrice}"
            generalProductCardDescription.text = product.productDescription
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