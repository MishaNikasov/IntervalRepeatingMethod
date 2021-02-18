package com.nikasov.intervalrepeatingmethod.ui.base

import androidx.fragment.app.Fragment
import com.nikasov.intervalrepeatingmethod.common.extentions.showToast
import java.lang.Exception

open class BaseFragment : Fragment() {
    fun displayError(exception: Exception) {
        requireContext().showToast(exception.localizedMessage ?: "Error")
    }
}