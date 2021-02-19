package com.nikasov.intervalrepeatingmethod.ui.adapter.binder

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikasov.intervalrepeatingmethod.common.extentions.getFormatTime
import java.util.*

@BindingAdapter("dateText")
fun TextView.setDateText(value: Date?) {
    value?.let {
        text = value.getFormatTime()
    }
}