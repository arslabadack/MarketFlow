package com.example.marketflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketflow.databinding.ActivityMainBinding
import java.util.ArrayList

abstract class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val itemList: ArrayList<Item> = generateList(1)
    private val adapter = ItemAdapter(itemList, this)

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
    }

    fun insertProduct(view: View) {
        val product: EditText = binding.productTxtEdit
        val information: EditText = binding.productInfoTxtEdit
        val quantity: EditText = binding.productQuantityTxtEdit

        val productAdd: Item? = Item(
            R.drawable.market_cart,
            product.text.toString(),
            information.text.toString(),
            quantity.text.toString()
        )
        if (productAdd?.product?.isEmpty()!!) {
            Toast.makeText(this, "Qual o produto mesmo?", Toast.LENGTH_SHORT).show()
        } else if (productAdd?.info.isEmpty()!!) {
            Toast.makeText(this, "Descreva seu produto", Toast.LENGTH_SHORT).show()
        } else if (productAdd?.quantity.isEmpty()!!) {
            Toast.makeText(this, "Vamos comprar quanto?", Toast.LENGTH_SHORT).show()
        }else {
            itemList.add(productAdd)
        }
        adapter.notifyItemInserted(itemList.size)
        Toast.makeText(this, "Produto adicionado", Toast.LENGTH_SHORT).show()


    }

    fun editProduct(view: View) {

    }

    fun removeProduct(view: View) {
        var clickedPosition: Item = onItemClick()
        itemList.remove(clickedPosition)
    }


    fun generateList (size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        for(i in 0 until size){
            val item = Item(R.drawable.market_cart, "Produto $i", "Informações sobre o produto", "0")
            list.add(item)
        }
        return list
    }

    override fun onItemClick(position: Int): Item{
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: Item = itemList[position]

        adapter.notifyItemChanged(position)

        return clickedItem
    }
}