package com.example.project1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_registration)

        val username: EditText = findViewById(R.id.username_field)
        val email: EditText = findViewById(R.id.email_field)
        val password: EditText = findViewById(R.id.password_field)
        val shopName: EditText = findViewById(R.id.shopname_field)

        // Обработка нажатия кнопки "Подтвердить"
        val submitButton: Button = findViewById(R.id.confirm_button)

        submitButton.setOnClickListener {

        }


    }

}