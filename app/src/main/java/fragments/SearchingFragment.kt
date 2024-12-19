package fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.App
import com.example.project1.ProductDao
import com.example.project1.ProductDetailsFragment
import com.example.project1.ProductEntity
import com.example.project1.R
import com.example.project1.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchingFragment : Fragment(R.layout.searching_page_activity) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDao: ProductDao
    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация компонентов
        recyclerView = view.findViewById(R.id.recycler_view)
        searchView = view.findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Инициализация базы данных
        productDao = (requireActivity().application as App).database.productDao()

        // Загрузка данных
        loadProducts()

        // Настройка SearchView
        setupSearchView()
    }

    private fun loadProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val products = productDao.getAllProducts()
            withContext(Dispatchers.Main) {
                productAdapter = ProductAdapter(products) { product -> openProductDetails(product) }
                recyclerView.adapter = productAdapter
            }
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterProducts(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterProducts(it) }
                return true
            }
        })
    }

    private fun filterProducts(query: String) {
        // Если поиск пустой, отображаем все товары
        val filteredList = if (query.isEmpty()) {
            productAdapter.products // Все товары
        } else {
            productAdapter.products.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }
        productAdapter.updateData(filteredList)
    }

    private fun openProductDetails(product: ProductEntity) {
        val fragment = ProductDetailsFragment.newInstance(product.name, product.price, product.imageResId, product.description)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}


