package com.nikasov.intervalrepeatingmethod.ui.adapter.binder

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator

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

@BindingAdapter("carouselCount", "carouselProgress")
fun TextView.setCarouselProgression(carouselCount: Int?, carouselProgress: Int?) {
    carouselCount?.let {
        val progressionText = "$carouselProgress/$carouselCount"
        text = progressionText
    }
}

@BindingAdapter("animateProgress")
fun LinearProgressIndicator.animateProgress(progress: Int?) {
    progress?.let {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            this.setProgress(progress, true)
        } else {
            this.progress = progress
        }
    }
}