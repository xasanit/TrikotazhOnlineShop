package fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.AppDatabase
import com.example.project1.ProductDao
import com.example.project1.ProductDetailsFragment
import com.example.project1.ProductEntity
import com.example.project1.R
import com.example.project1.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasketFragment : Fragment(R.layout.basket_activity) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDao: ProductDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация компонентов
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Инициализация базы данных
        val database = AppDatabase.getDatabase(requireContext())
        productDao = database.productDao()

        // Загружаем товары из корзины
        loadBasketProducts()

    }

    private fun loadBasketProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val basketProducts = productDao.getAllProducts().filter { it.isInBasket }
            if (basketProducts.isEmpty()) {
                withContext(Dispatchers.Main) {
                    // Если корзина пуста, скрываем RecyclerView и отображаем TextView
                    recyclerView.visibility = View.GONE
                    view?.findViewById<View>(R.id.nothing_to_show)?.visibility = View.VISIBLE
                }
            }
            withContext(Dispatchers.Main) {
                // Используем уже существующий адаптер
                productAdapter = ProductAdapter(basketProducts) {
                    product -> openProductDetails(product) }
                recyclerView.adapter = productAdapter
            }
        }
    }

    private fun removeFromBasket(product: ProductEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            // Обновляем поле isInBasket на false, чтобы удалить товар из корзины
            productDao.removeFromBasket(product.id)
            loadBasketProducts() // Обновляем корзину
        }
    }

    private fun openProductDetails(product: ProductEntity) {
        val fragment = ProductDetailsFragment.newInstance(
            id = product.id, // Передаем id товара
            name = product.name,
            price = product.price,
            imageResId = product.imageResId,
            description = product.description,
            isInBasket = product.isInBasket,
            shop = product.shop,
            kategory = product.kategory
        )
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
