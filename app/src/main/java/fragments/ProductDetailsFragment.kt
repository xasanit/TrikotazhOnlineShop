package com.example.project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_product_details, container, false)

        // Получаем данные из arguments
        val productName = arguments?.getString("name")
        val productPrice = arguments?.getString("price")
        val productImageResId = arguments?.getInt("imageResId", 0)
        val productDescription = arguments?.getString("description")

        // Устанавливаем данные в соответствующие элементы
        view.findViewById<TextView>(R.id.product_name).text = productName
        view.findViewById<TextView>(R.id.product_price).text = productPrice
        view.findViewById<ImageView>(R.id.product_image).setImageResource(productImageResId ?: 0)
        view.findViewById<TextView>(R.id.product_description).text = productDescription

        return view
    }

    companion object {
        fun newInstance(name: String, price: String, imageResId: Int, description: String): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putString("name", name)
            args.putString("price", price)
            args.putInt("imageResId", imageResId)
            args.putString("description", description)  // Добавляем описание
            fragment.arguments = args
            return fragment
        }
    }
}

