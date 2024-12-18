package fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.project1.R

class BasketFragment : Fragment(R.layout.basket_activity) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ваша логика корзины, например, получение элементов и отображение их в RecyclerView
        val returned = arguments?.getBoolean("RETURNED", false) ?: false
        if (returned) {
            // Если нужно, обновите UI
        }
    }
}
