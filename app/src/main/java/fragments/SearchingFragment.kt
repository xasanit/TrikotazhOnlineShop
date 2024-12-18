package fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.project1.R

class SearchingFragment : Fragment(R.layout.searching_page_activity) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ваша логика для поиска или других UI компонентов
        val returned = arguments?.getBoolean("RETURNED", false) ?: false
        if (returned) {
            // Если необходимо обновить элементы, можно добавить логику
        }
    }
}
