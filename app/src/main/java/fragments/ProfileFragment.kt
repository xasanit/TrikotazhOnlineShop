package fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.project1.R

class ProfileFragment : Fragment(R.layout.profile_activity) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Логика для отображения информации о профиле, если нужно
        val returned = arguments?.getBoolean("RETURNED", false) ?: false
        if (returned) {
            // Если необходимо, обновите UI
        }
    }
}
