import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project1.BasketActivity
import com.example.project1.HomePageActivity
import com.example.project1.ProfileActivity
import com.example.project1.R
import com.example.project1.SearchingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.BasketFragment
import fragments.HomePageFragment
import fragments.ProfileFragment
import fragments.SearchingFragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId()) // У каждой активности должен быть метод для получения layout
        setupBottomNavigation()
    }

    protected abstract fun getLayoutResId(): Int

    protected fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            val targetFragment = when (item.itemId) {
                R.id.home_page -> HomePageFragment()
                R.id.search_page -> SearchingFragment() // Поменяйте на ваш фрагмент для поиска
                R.id.basket_page -> BasketFragment() // Поменяйте на ваш фрагмент для корзины
                R.id.profile_page -> ProfileFragment() // Поменяйте на ваш фрагмент для профиля
                else -> null
            }

            if (targetFragment != null) {
                replaceFragment(targetFragment)
                true
            } else {
                Log.d("BottomNavigation", "No action taken")
                false
            }
        }
    }

    // Метод для замены фрагмента
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        updateSelectedMenuItem()
    }

    // Обновляем выделение меню, если нужно
    protected fun updateSelectedMenuItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        // Логика обновления выбранного пункта меню
    }
}


