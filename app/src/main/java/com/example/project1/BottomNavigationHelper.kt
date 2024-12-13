package com.example.project1

import android.content.Context
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationHelper {
    companion object {
        fun setupNavigation(bottomNavigationView: BottomNavigationView, context: Context) {
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home_page -> {
                        if (context is HomePageActivity) return@setOnItemSelectedListener false
                        val intent = Intent(context, HomePageActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT // Изменение флага
                        context.startActivity(intent)
                        true
                    }
                    R.id.search_page -> {
                        if (context is SearchingActivity) return@setOnItemSelectedListener false
                        val intent = Intent(context, SearchingActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                        true
                    }
                    R.id.basket_page -> {
                        if (context is BasketActivity) return@setOnItemSelectedListener false
                        val intent = Intent(context, BasketActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                        true
                    }
                    R.id.profile_page -> {
                        if (context is ProfileActivity) return@setOnItemSelectedListener false
                        val intent = Intent(context, ProfileActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
    }
}
