package com.example.essstore.data

import android.content.DialogInterface
import android.util.Log
import com.example.essstore.databinding.GeneralProductCardBinding
import com.example.essstore.favourite.favouriteProduct
import com.example.essstore.favourite.favouriteProductViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.cart.cartProduct
import com.example.essstore.cart.cartProductViewModel
import com.example.essstore.common.Common.PRODUCT_ADDED_TO_CART
import com.example.essstore.common.Common.makeToast
import com.example.essstore.databinding.FavouriteProductCardBinding
import com.example.essstore.view.FavouriteProducts
import com.squareup.picasso.Picasso

class FavouriteProductAdapter(mfavouriteProductViewModel: favouriteProductViewModel, mCartViewModel: cartProductViewModel): RecyclerView.Adapter<FavouriteProductAdapter.favouriteViewHolder>(){
    var productList = emptyList<favouriteProduct>()
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
            var url = "${product.productImage}"
//            url = "https://www.lays.com/sites/lays.com/files/2021-07/XL%20Lay%27s%20Flamin%27%20Hot.png"
            Log.d("Box","$url")
            Picasso.get().load(url).fit().centerCrop().into(favouriteProductCardImage)
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
            makeToast(holder.itemView.context, PRODUCT_ADDED_TO_CART)
        }

        holder.binding.favouriteProductDeleteItem.setOnClickListener{
            deleteProductAlert(holder.itemView, product)
        }
    }

    fun setData(favouriteProducts: List<favouriteProduct>){
        this.productList = favouriteProducts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun deleteProductAlert(view: View, product: favouriteProduct){

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(view.context,
                "Product deleted", Toast.LENGTH_SHORT).show()
            mFavouriteViewmodel.deleteProduct(
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