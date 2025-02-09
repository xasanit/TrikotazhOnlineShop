package fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.project1.AddingNewItemActivity
import com.example.project1.App
import com.example.project1.MyItemsActivity
import com.example.project1.R
import com.example.project1.UserDao
import com.example.project1.UserEntity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.profile_activity) {

    private lateinit var userDao: UserDao
    private lateinit var shopNameTextView: TextView
    private lateinit var shopLogoImageView: ImageView
    private lateinit var addNewItem: Button
    private lateinit var showMyItems: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация UI элементов
        shopNameTextView = view.findViewById(R.id.shopname_textview)
        shopLogoImageView = view.findViewById(R.id.profile_shop_logo)
        addNewItem = view.findViewById(R.id.add_new_item_btn)
        showMyItems = view.findViewById(R.id.show_my_items_btn)


        // Инициализация базы данных и UserDao
        val database = (requireActivity().application as App).database
        userDao = database.userDao()

        // Получение данных текущего пользователя
        lifecycleScope.launch {
            val user = getCurrentUser()
            if (user != null) {
                // Отображаем данные пользователя
                shopNameTextView.text = user.shop
                shopLogoImageView.setImageResource(user.logoResId)
            } else {
                // Обработка ситуации, если пользователь не найден
                shopNameTextView.text = "Магазин не найден"
                shopLogoImageView.setImageResource(R.drawable.default_logo)
            }
        }

        addNewItem.setOnClickListener {
            lifecycleScope.launch {
                val user = getCurrentUser()
                if (user != null) {
                    val intent: Intent =
                        Intent(requireContext(), AddingNewItemActivity::class.java).apply {
                            putExtra("SHOP_NAME", user.shop)
                        }
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Магазин не найден", Toast.LENGTH_SHORT).show()
                }
            }
        }

        showMyItems.setOnClickListener {
            lifecycleScope.launch {
                val user = getCurrentUser()
                if (user != null) {
                    val intent: Intent =
                        Intent(requireContext(), MyItemsActivity::class.java).apply {
                            putExtra("SHOP_NAME", user.shop)
                        }
                    startActivity(intent)
                }
                else {
                    Toast.makeText(requireContext(), "Магазин не найден", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Функция для получения текущего пользователя
    suspend fun getCurrentUser(): UserEntity? {
        // Замените логику получения текущего пользователя на нужную вам
        val users = userDao.getAllUsers()
        return if (users.isNotEmpty()) users[0] else null
    }
}

