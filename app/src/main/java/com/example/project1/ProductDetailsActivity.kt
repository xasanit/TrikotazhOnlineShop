package com.example.project1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val productName = intent.getStringExtra("name")
        val productPrice = intent.getStringExtra("price")
        val productImageResId = intent.getIntExtra("imageResId", 0)

        findViewById<TextView>(R.id.product_name).text = productName
        findViewById<TextView>(R.id.product_price).text = productPrice
        findViewById<ImageView>(R.id.product_image).setImageResource(productImageResId)
    }
}
