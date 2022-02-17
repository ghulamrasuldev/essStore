package com.example.essstore.data


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.databinding.CartProductCardBinding

class CartAdapter(mCartViewModel: cartProductViewModel): RecyclerView.Adapter<CartAdapter.cartViewHolder>(){
    private var productList = emptyList<cartProduct>()
    val mCartViewmodel = mCartViewModel
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
        val product = productList[position]
        holder.binding.apply {
            cartProductTitle.text = product.productName
            cartProductPrice.text = "$ ${product.productPrice}"
            cartProductDescription.text = product.productDescription
            cartProductSelectedQuantity.text = product.selectedQuantity.toString()

        }

        holder.binding.cartProductDeleteItem.setOnClickListener{
            mCartViewmodel.deleteProduct(
                product.id
            )
        }

        holder.binding.cartProductIncrement.setOnClickListener{
            mCartViewmodel.updateProduct(
                product.id,
                product.selectedQuantity+1
            )
        }

        holder.binding.cartProductDecrement.setOnClickListener{
            mCartViewmodel.updateProduct(
                product.id,
                product.selectedQuantity-1
            )
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