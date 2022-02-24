package com.example.essstore.data


import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.CartProductCardBinding
import com.squareup.picasso.Picasso

class CartAdapter(mCartViewModel: cartProductViewModel): RecyclerView.Adapter<CartAdapter.cartViewHolder>(){
    var productList = emptyList<cartProduct>()
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
            var url = "${product.img}"
//            url = "https://www.lays.com/sites/lays.com/files/2021-07/XL%20Lay%27s%20Flamin%27%20Hot.png"
            Log.d("Box","$url")
            Picasso.get().load(url).fit().centerCrop().into(cartProductImage)
        }

        holder.binding.cartProductDeleteItem.setOnClickListener{
            deleteProductAlert(holder.itemView, product)
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

    fun deleteProductAlert(view: View, product: cartProduct){

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(view.context,
                "Product deleted", Toast.LENGTH_SHORT).show()
            mCartViewmodel.deleteProduct(
                product.id
            )
        }
        val negativeButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(view.context,
                "Action reverted!", Toast.LENGTH_SHORT).show()
        }

        val builder = AlertDialog.Builder(view.context)
        with(builder)
        {
            setTitle("Delete product from Cart? ")
            setMessage("Please confirm that you want to delete product from your cart.")
            setPositiveButton("OK", DialogInterface.OnClickListener(positiveButtonClick))
            setNegativeButton("No", DialogInterface.OnClickListener(negativeButtonClick))
            show()
        }
    }
}