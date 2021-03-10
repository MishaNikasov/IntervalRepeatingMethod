package com.nikasov.intervalrepeatingmethod.ui.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nikasov.intervalrepeatingmethod.common.extentions.showToast
import java.lang.Exception

abstract class BaseFragment<Binding: ViewDataBinding> : Fragment() {
    lateinit var binding: Binding
    fun displayError(exception: Exception) {
        requireContext().showToast(exception.localizedMessage ?: "Error")
    }
}