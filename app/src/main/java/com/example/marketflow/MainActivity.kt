package com.example.marketflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val itemList: ArrayList<Item> = generateList(100)
    private val adapter = ItemAdapter(itemList)

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
}