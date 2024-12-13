import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.BasketActivity
import com.example.project1.HomePageActivity
import com.example.project1.ProfileActivity
import com.example.project1.R
import com.example.project1.SearchingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId()) // У каждой активности должен быть метод для получения layout
        setupBottomNavigation(this::class.java)
    }

    protected abstract fun getLayoutResId(): Int

    protected fun setupBottomNavigation(currentActivity: Class<*>) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Устанавливаем выбранный элемент меню
        val selectedItemId = when (currentActivity) {
            HomePageActivity::class.java -> R.id.home_page
            SearchingActivity::class.java -> R.id.search_page
            BasketActivity::class.java -> R.id.basket_page
            ProfileActivity::class.java -> R.id.profile_page
            else -> null
        }
        if (selectedItemId != null) {
            bottomNavigationView.selectedItemId = selectedItemId
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            val targetActivity = when (item.itemId) {
                R.id.home_page -> HomePageActivity::class.java
                R.id.search_page -> SearchingActivity::class.java
                R.id.basket_page -> BasketActivity::class.java
                R.id.profile_page -> ProfileActivity::class.java
                else -> null
            }

            Log.d("BottomNavigation", "Item selected: ${item.itemId}, Target activity: $targetActivity")

            if (targetActivity != null && currentActivity != targetActivity) {
                val intent = Intent(this, targetActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                Log.d("BottomNavigation", "Activity started: $targetActivity")
//                finish() // Закрываем текущую активность
                true
            } else {
                Log.d("BottomNavigation", "No action taken")
                false
            }
        }
    }
}

