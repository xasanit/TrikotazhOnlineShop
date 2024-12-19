package com.example.project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    private lateinit var productDao: ProductDao
    private lateinit var productDetailsViewModel: ProductDetailsViewModel

    private var productId: Int? = null
    private var productName: String? = null
    private var productPrice: String? = null
    private var productImageResId: Int = 0
    private var productDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_product_details, container, false)

        // Инициализируем базу данных
        val database = AppDatabase.getDatabase(requireContext())
        productDao = database.productDao()

        // Получаем данные из arguments
        arguments?.let {
            productId = it.getInt("id")
            productName = it.getString("name")
            productPrice = it.getString("price")
            productImageResId = it.getInt("imageResId", 0)
            productDescription = it.getString("description")
        }

        // Инициализация ViewModel
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)

        // Настройка UI
        val nameTextView = view.findViewById<TextView>(R.id.product_name)
        val priceTextView = view.findViewById<TextView>(R.id.product_price)
        val descriptionTextView = view.findViewById<TextView>(R.id.product_description)
        val imageView = view.findViewById<ImageView>(R.id.product_image)

        // Устанавливаем данные в UI
        nameTextView.text = productName
        priceTextView.text = productPrice
        descriptionTextView.text = productDescription
        imageView.setImageResource(productImageResId)

        // Настройка кнопки "Добавить в корзину"
        val addToBasketButton = view.findViewById<Button>(R.id.btn_add_to_basket)
        updateAddToBasketButton(addToBasketButton)

        addToBasketButton.setOnClickListener {
            productId?.let {
                lifecycleScope.launch {
                    if (productDetailsViewModel.isInBasket) {
                        productDao.removeFromBasket(it)
                        Toast.makeText(requireContext(), "Товар удален из корзины", Toast.LENGTH_SHORT).show()
                        productDetailsViewModel.isInBasket = false
                    } else {
                        productDao.addToBasket(it)
                        Toast.makeText(requireContext(), "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
                        productDetailsViewModel.isInBasket = true
                    }
                    updateAddToBasketButton(addToBasketButton)
                }
            }
        }

        return view
    }

    private fun updateAddToBasketButton(button: Button) {
        if (productDetailsViewModel.isInBasket) {
            button.text = "Убрать из корзины"
        } else {
            button.text = "Добавить в корзину"
        }
    }

    companion object {
        fun newInstance(id: Int, name: String, price: String, imageResId: Int, description: String, isInBasket: Boolean): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle().apply {
                putInt("id", id)
                putString("name", name)
                putString("price", price)
                putInt("imageResId", imageResId)
                putString("description", description)
                putBoolean("isInBasket", isInBasket)  // Добавляем isInBasket
            }
            fragment.arguments = args
            return fragment
        }
    }

}



