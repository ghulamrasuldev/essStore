package com.example.essstore.data

import android.content.Intent
import com.example.essstore.databinding.HistoryCardBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.essstore.view.HistoryOrderDetails

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.historyViewHolder>(){
    inner class historyViewHolder (val binding: HistoryCardBinding): RecyclerView.ViewHolder(binding.root)

    private val difCallBack= object : DiffUtil.ItemCallback<orderHistoryResponse>(){
        override fun areItemsTheSame(oldItem: orderHistoryResponse, newItem: orderHistoryResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: orderHistoryResponse, newItem: orderHistoryResponse): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, difCallBack)
    var products: List<orderHistoryResponse>
        set(value) {
            differ.submitList(value)
        }
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.historyViewHolder {
        return historyViewHolder(
            HistoryCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.historyViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            historyDate.text = product.placed
            paymentMethod.text = product.paymentMethod
            historyProductQuantity.text = product.products.size.toString()
        }

        holder.binding.historySeeDetails.setOnClickListener{
            val intent = Intent(holder.itemView.context, HistoryOrderDetails::class.java)
            intent.putExtra("id", product.id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
}