package com.example.project1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.adapter.ProductAdapter
import kotlinx.coroutines.launch

class MyItemsActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_items_page)

        val shopName = intent.getStringExtra("SHOP_NAME") ?: "Магазин не найден"
        val productDao: ProductDao = AppDatabase.getDatabase(this).productDao()

        // Инициализация RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(emptyList()) { product ->
            // Обработчик клика по элементу
            Toast.makeText(this, "Вы выбрали: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        // Загрузка данных из базы
        lifecycleScope.launch {
            val products = productDao.getProductsByShop(shopName)
            adapter.updateData(products)

            if (products.isEmpty()) {
                Toast.makeText(this@MyItemsActivity, "Нет товаров для магазина $shopName", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

