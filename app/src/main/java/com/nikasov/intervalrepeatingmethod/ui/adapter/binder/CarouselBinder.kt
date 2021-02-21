package com.nikasov.intervalrepeatingmethod.ui.adapter.binder

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("carouselWordStatus")
fun TextView.setCarouselWordStatus(status: Boolean?) {
    status?.let {
        text = if (status) {
            "Completed"
        } else {
            "Failed"
        }
    }
}