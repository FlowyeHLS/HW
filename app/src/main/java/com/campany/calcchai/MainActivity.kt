package com.campany.calcchai

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editItem = findViewById<EditText>(R.id.editItem)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listItems = findViewById<ListView>(R.id.listItems)

        val shoppingList = ArrayList<String>()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            shoppingList
        )

        listItems.adapter = adapter

        buttonAdd.setOnClickListener {

            val item = editItem.text.toString().trim()

            if (item.isEmpty()) {
                editItem.error = "Введите название товара"
                return@setOnClickListener
            }

            adapter.add(item)

            editItem.text.clear()

            Toast.makeText(this, "Товар добавлен", Toast.LENGTH_SHORT).show()
        }

        listItems.setOnItemClickListener { _, _, position, _ ->

            val removedItem = adapter.getItem(position)

            adapter.remove(removedItem)

            Toast.makeText(
                this,
                "Удалено: $removedItem",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}