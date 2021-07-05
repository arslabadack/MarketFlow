package com.example.marketflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList:List<Item>,
    val listener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imageView:ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
        val infoTextView: TextView = itemView.findViewById(R.id.infoTextView)


        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.titleTextView.setText(currentItem.product)
        holder.infoTextView.setText(currentItem.info)
    }

    override fun getItemCount() = itemList.size

}

