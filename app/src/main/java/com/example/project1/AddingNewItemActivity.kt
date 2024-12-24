package com.example.project1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddingNewItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adding_new_item_page)

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val confirmButton = findViewById<Button>(R.id.add_new_item_button)
        val itemName = findViewById<EditText>(R.id.new_item_name)
        val itemPrice = findViewById<EditText>(R.id.new_item_price)
        val itemDescription = findViewById<EditText>(R.id.new_item_description)
        val itemImage = findViewById<ImageView>(R.id.new_item_img)
        val shopName = intent.getStringExtra("SHOP_NAME") ?: "Магазин не найден"

        val database = AppDatabase.getDatabase(this)
        val productDao = database.productDao()
        val userDao = database.userDao()

        confirmButton.setOnClickListener {
            val name = itemName.text.toString().trim()
            val price = itemPrice.text.toString().trim()
            val description = itemDescription.text.toString().trim()
            val imageResId = R.drawable.default_logo
            val shop = shopName

            if(name.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty()) {

                val newProduct = ProductEntity(
                    name = name,
                    price = price,
                    imageResId = imageResId,
                    description = description,
                    isInBasket = false,
                    shop = shop
                )
                lifecycleScope.launch {
                    productDao.insertProduct(newProduct)
                    Toast.makeText(this@AddingNewItemActivity, "Товар успешно добавлен", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
        cancelButton.setOnClickListener {
            finish()
        }
    }
}