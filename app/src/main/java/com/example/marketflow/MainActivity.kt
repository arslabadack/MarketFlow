package com.example.marketflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketflow.databinding.ActivityMainBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity(), OnItemClickListener {

   private val itemList: ArrayList<Item> = generateList(0)
    private val adapter = ItemAdapter(itemList, this)
    private var productIndex: Int = 0

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

    fun createItem(): Item? {
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
        }else{
            return productAdd
            product.clearComposingText()
        }

        return null
    }

    fun insertProduct(view: View) {
        val insertProduct: Item? = createItem()
        if(insertProduct != null) {
            itemList.add(insertProduct)
            adapter.notifyItemInserted(itemList.size)

            Toast.makeText(this, "Produto adicionado", Toast.LENGTH_SHORT).show()
        }
    }

   fun editProduct(view: View) {
       val editProduct: Item? = createItem()
       if (editProduct != null) {
           itemList[productIndex] = editProduct
           adapter.notifyItemChanged(productIndex)

           Toast.makeText(this, "Produto modificado", Toast.LENGTH_SHORT).show()
       }
    }

    fun removeProduct(view: View) {
        if (productIndex != -1){
            itemList.removeAt(productIndex)
            adapter.notifyItemRemoved(productIndex)

            Toast.makeText(this, "Produto removido", Toast.LENGTH_LONG).show()
            productIndex = -1
        }else {
            Toast.makeText(this, "Qual produto quer remover?", Toast.LENGTH_SHORT).show()
        }
    }


    fun generateList (size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        return list
    }

    override fun onItemClick(position: Int) {
        val item: Item = itemList[position]

        val clickedProduct: EditText = binding.productTxtEdit
        clickedProduct.setText(item.product)
        val clickedInfo: EditText = binding.productInfoTxtEdit
        clickedInfo.setText(item.info)
        val clickedQuantity: EditText = binding.productQuantityTxtEdit
        clickedQuantity.setText(item.quantity!!)

        productIndex = position
        adapter.notifyItemChanged(position)
    }
}



