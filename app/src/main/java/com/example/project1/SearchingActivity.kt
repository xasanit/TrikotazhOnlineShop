package com.example.project1

import BaseActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.SearchingFragment

class SearchingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Добавляем фрагмент в контейнер
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchingFragment())
                .commit()
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_container // Контейнер для фрагментов
    }
}
