package com.example.project1

import BaseActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BasketActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_activity)
        setupBottomNavigation(this::class.java)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }

    override fun getLayoutResId(): Int {
        return R.layout.basket_activity
    }
}