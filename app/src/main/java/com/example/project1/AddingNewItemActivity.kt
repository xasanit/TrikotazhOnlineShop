package com.example.project1

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.IOException

@Suppress("DEPRECATION", "UNUSED_EXPRESSION")
class AddingNewItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adding_new_item_page)

        val kategories: Array<String>
        kategories = arrayOf("Толстовки и худи", "Обувь", "Аксессуары")

//        var selectedImageUri: Uri? = null

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val confirmButton = findViewById<Button>(R.id.add_new_item_button)
        val itemName = findViewById<EditText>(R.id.new_item_name)
        val itemPrice = findViewById<EditText>(R.id.new_item_price)
        val itemDescription = findViewById<EditText>(R.id.new_item_description)
        val itemImage = findViewById<ImageView>(R.id.new_item_img)
        val shopName = intent.getStringExtra("SHOP_NAME") ?: "Магазин не найден"
        val kategorySpinner = findViewById<Spinner>(R.id.action_bar_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kategories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        kategorySpinner.adapter = adapter

        val database = AppDatabase.getDatabase(this)
        val productDao = database.productDao()

        itemImage.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.setType("image/*")
            startActivityForResult(photoPickerIntent, 1)
        }

//        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//            if (requestCode == 1 && resultCode == RESULT_OK) {
//                selectedImageUri = data?.data
//                // Устанавливаем изображение в ImageView
//                itemImage.setImageURI(selectedImageUri)
//            }
//        }

        confirmButton.setOnClickListener {
            val name = itemName.text.toString().trim()
            val price = itemPrice.text.toString().trim()
            val description = itemDescription.text.toString().trim()
//            val imageResId = selectedImageUri?.toString() ?: "Изображение не найдено!"
            val imageResId = R.drawable.default_logo
            val shop = shopName
            val kategory = kategorySpinner.selectedItem.toString()


            if (name.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty()) {

                val newProduct = ProductEntity(
                    name = name,
                    price = price,
                    imageResId = imageResId,
                    description = description,
                    isInBasket = false,
                    shop = shop,
                    kategory = kategory
                )
                lifecycleScope.launch {
                    productDao.insertProduct(newProduct)
                    Toast.makeText(
                        this@AddingNewItemActivity,
                        "Товар успешно добавлен",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
        cancelButton.setOnClickListener {
            finish()
        }
    }
}