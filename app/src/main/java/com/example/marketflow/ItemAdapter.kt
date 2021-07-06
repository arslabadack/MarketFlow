package com.example.marketflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketflow.databinding.ItemBinding

class ItemAdapter(private val itemList:List<Item>,
    val listener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val itemBinding: ItemBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{
        init{
            itemView.setOnClickListener(this)
        }

        fun bind(item: Item){
            itemBinding.imageView.setImageResource(item.image)
            itemBinding.productTextView.text = item.product
            itemBinding.infoTextView.text = item.info
            itemBinding.quantityTextView.text = item.quantity.toString()
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size

}

