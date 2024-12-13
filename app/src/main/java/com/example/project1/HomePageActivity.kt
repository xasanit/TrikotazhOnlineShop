package com.example.project1

import BaseActivity
import ProductAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_activity)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val products = listOf(
            Product("Hoodie", "$1000", R.drawable._e753dae28676fa25c6d5b98d714507e),
            Product("Tolstovka", "$500", R.drawable.fc90acea67790611d62567819f0e2f),
            Product("Kofta naxuii", "$300", R.drawable.c6d22a2206a39a3cdfed4d9aad088ed0)
        )

        recyclerView.adapter = ProductAdapter(products)
        setupBottomNavigation(this::class.java)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        Log.d("BottomNav", "BottomNavigationView initialized: $bottomNavigationView")


    }

    override fun getLayoutResId(): Int {
        return R.layout.home_page_activity
    }
}