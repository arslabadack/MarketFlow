package com.example.marketflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val itemList: ArrayList<Item> = generateList(10)
    private val adapter = ItemAdapter(itemList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun generateList (size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        for(i in 0 until size){
            val item = Item(R.drawable.market_cart, "item $i", "info")
            list.add(item)
        }
        return list
    }

    fun insertButton(view: View) {
        val item = Item(R.drawable.market_cart,"New Item", "info")
        val position = (1..8).random()
        itemList.add(position,item)
        adapter.notifyItemInserted(position)
    }


    fun removeButton(view: View) {
        val position = (0..8).random()
        itemList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Clicked on ${itemList[position].product}", Toast.LENGTH_SHORT).show()
        adapter.notifyItemChanged(position)
    }
}