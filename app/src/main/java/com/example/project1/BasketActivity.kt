package com.example.project1

import BaseActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.BasketFragment

class BasketActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Добавление фрагмента в контейнер при первом запуске
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BasketFragment())
                .commit()
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_container // Контейнер для фрагментов
    }
}
