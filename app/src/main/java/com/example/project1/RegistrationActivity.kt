package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_registration)

        // Инициализируем DAO
        val database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        // Инициализация UI
        val usernameField: EditText = findViewById(R.id.username_field)
        val emailField: EditText = findViewById(R.id.email_field)
        val passwordField: EditText = findViewById(R.id.password_field)
        val shopNameField: EditText = findViewById(R.id.shopname_field)
        val submitButton: Button = findViewById(R.id.confirm_button)

        // Обработка нажатия кнопки "Подтвердить"
        submitButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val shopName = shopNameField.text.toString().trim()

            // Проверка на пустоту полей
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || shopName.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Сохраняем пользователя
            val newUser = UserEntity(
                username = username,
                email = email,
                password = password,
                shop = shopName,
                logoResId = R.drawable.default_logo // Предположим, что у вас есть логотип по умолчанию
            )

            lifecycleScope.launch {
                userDao.insertUser(newUser)
                Toast.makeText(this@RegistrationActivity, "Регистрация успешно завершена!", Toast.LENGTH_SHORT).show()

                // Опционально: переход на другую страницу после успешной регистрации
                val intent = Intent(this@RegistrationActivity, HomePageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
