package fragments

import ProductAdapter
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Product
import com.example.project1.ProductDetailsActivity
import com.example.project1.R

@Suppress("DEPRECATION")
class HomePageFragment : Fragment(R.layout.home_page_activity) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val products = listOf(
        Product("Hoodie", "$1000", R.drawable._e753dae28676fa25c6d5b98d714507e),
        Product("Tolstovka", "$500", R.drawable.fc90acea67790611d62567819f0e2f),
        Product("Kofta naxuii", "$300", R.drawable.c6d22a2206a39a3cdfed4d9aad088ed0)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настройка RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productAdapter = ProductAdapter(products) { product -> openProductDetails(product) }
        recyclerView.adapter = productAdapter

        // Обработка поиска, если нужно
        val searchView: SearchView = view.findViewById(R.id.searchView)
        // Добавьте логику поиска, если необходимо
    }

    private fun openProductDetails(product: Product) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("name", product.name)
        intent.putExtra("price", product.price)
        intent.putExtra("imageResId", product.imageResId)
        startActivity(intent)
    }
}
