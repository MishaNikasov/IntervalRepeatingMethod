package com.nikasov.intervalrepeatingmethod.ui.cusom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.databinding.ItemStepProgressHolderBinding

@SuppressLint("CustomViewStyleable")
class StepProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var maxProgress: Int = 0
    private var progress: Int = 0

    private var placeholderColor: Int = ContextCompat.getColor(context, R.color.athens_gray)
    private var progressColor: Int = ContextCompat.getColor(context, R.color.emerald)

    init {
        orientation = HORIZONTAL

        attrs?.let {
            val attributeArray =
                context.obtainStyledAttributes(it, R.styleable.step_progress_attribute, 0, 0)

            maxProgress = attributeArray.getInt(R.styleable.step_progress_attribute_max_progress, maxProgress)
            progress = attributeArray.getInt(R.styleable.step_progress_attribute_progress, progress)
            placeholderColor = attributeArray.getInt(R.styleable.step_progress_attribute_placeholder_color, placeholderColor)
            progressColor = attributeArray.getInt(R.styleable.step_progress_attribute_progress_color, progressColor)

            attributeArray.recycle()
        }

        removeAllViews()

        setMaxProgress()
        setProgress()
    }

    fun setPlaceholderColor(color: Int) {
        placeholderColor = ContextCompat.getColor(context, color)
    }

    fun setMaxProgress(maxProgress: Int = this.maxProgress) {
        this.maxProgress = maxProgress
        repeat(maxProgress) {
            val binding = ItemStepProgressHolderBinding.inflate(
                LayoutInflater.from(this.context),
                this,
                false
            )
            binding.placeholder.setCardBackgroundColor(placeholderColor)
            binding.root.seLayoutWeight(1.0f)
            addView(binding.root)
        }
    }

    fun setProgress(progress: Int = this.progress) {
        this.progress = progress
        repeat(progress) {
            (getChildAt(it) as MaterialCardView).setCardBackgroundColor(progressColor)
        }
    }

    private fun refresh() {
        setMaxProgress()
        setProgress()
    }

    fun setViewPager(vp: ViewPager2) {
        if (vp.adapter == null) {
            return
        }
        vp.adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                maxProgress = vp.adapter?.itemCount ?: 0
                refresh()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                maxProgress = vp.adapter?.itemCount ?: 0
                refresh()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                maxProgress = vp.adapter?.itemCount ?: 0
                refresh()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                maxProgress = vp.adapter?.itemCount ?: 0
                refresh()
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                maxProgress = vp.adapter?.itemCount ?: 0
                refresh()
            }
        })
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setProgress(position)
            }
        })
    }

    private fun View.seLayoutWeight(layoutWeight: Float) {
        post {
            val param = layoutParams as LayoutParams
            param.weight = layoutWeight
            layoutParams = param
        }
    }

}