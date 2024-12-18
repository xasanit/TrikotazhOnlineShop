package com.example.project1

import BaseActivity
import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.HomePageFragment

class HomePageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Добавление фрагмента при первом запуске активности
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomePageFragment())
                .commit()
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_container // Контейнер для фрагментов
    }
}
