package fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.App
import com.example.project1.ProductDao
import com.example.project1.ProductDetailsFragment
import com.example.project1.ProductEntity
import com.example.project1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.project1.adapter.ProductAdapter

@Suppress("DEPRECATION")
class HomePageFragment : Fragment(R.layout.home_page_activity) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDao: ProductDao

    // Список продуктов для добавления в базу данных, если она пустая
    private val defaultProducts = listOf(
        ProductEntity(name = "Hoodie", price = "$1000", imageResId = R.drawable._e753dae28676fa25c6d5b98d714507e, description = "Комфортный и очень удобный худи по выгодной цене!", isInBasket = false, shop = "Adidas"),
        ProductEntity(name = "Tolstovka", price = "$500", imageResId = R.drawable.fc90acea67790611d62567819f0e2f, description = "Крутая мощная молодежная толстовка", isInBasket = false, shop = "Nike"),
        ProductEntity(name = "Kofta", price = "$300", imageResId = R.drawable.c6d22a2206a39a3cdfed4d9aad088ed0, description = "У этой одежды нет конкурентов на этот сезон!", isInBasket = false, shop = "Puma")
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация productDao
        productDao = (requireActivity().application as App).database.productDao()

        // Проверяем, есть ли товары в базе данных, если нет - добавляем дефолтные
        checkAndAddProductsToDatabase()

        // Настройка RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Загружаем товары из базы данных
        loadProductsFromDatabase()

        // Обработка поиска, если нужно
        val searchView: SearchView = view.findViewById(R.id.searchView)
        // Логика поиска товаров по имени (если необходимо)
    }

    // Функция для добавления товаров в базу данных
    private fun checkAndAddProductsToDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val productsInDb = productDao.getAllProducts()
            if (productsInDb.isEmpty()) {
                // Если в базе данных нет продуктов, добавляем дефолтные
                for (product in defaultProducts) {
                    productDao.insertProduct(product)
                }
            }
        }
    }

    // Функция для загрузки товаров из базы данных
    private fun loadProductsFromDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val productEntities = productDao.getAllProducts() // Получаем все продукты из базы данных
            withContext(Dispatchers.Main) {
                productAdapter = ProductAdapter(productEntities) { product -> openProductDetails(product) }
                recyclerView.adapter = productAdapter
            }
        }
    }

    // Открытие фрагмента с деталями товара
    private fun openProductDetails(product: ProductEntity) {
        val fragment = ProductDetailsFragment.newInstance(
            id = product.id, // Передаем id товара
            name = product.name,
            price = product.price,
            imageResId = product.imageResId,
            description = product.description,
            isInBasket = product.isInBasket,
            shop = product.shop
        )
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

