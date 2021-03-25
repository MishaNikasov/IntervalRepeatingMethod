package com.nikasov.intervalrepeatingmethod.ui.adapter.pager

import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.ui.entity.WordModel
import com.nikasov.intervalrepeatingmethod.databinding.ItemCarouselBinding
import com.nikasov.intervalrepeatingmethod.ui.adapter.base.BaseAdapter
import javax.inject.Inject

class CarouselAdapter @Inject constructor() : BaseAdapter<WordModel, ItemCarouselBinding>() {

    override var differ = AsyncListDiffer(this, callback)
    override var layoutId = R.layout.item_carousel

    var interaction: Interaction? = null

    interface Interaction {
        fun setResult(wordModel: WordModel, isCompleted: Boolean)
    }

    override fun bind(binding: ItemCarouselBinding, model: WordModel, position: Int) {
        binding.word = model
        binding.checkBtn.setOnClickListener {
            if (model.rus == binding.rusEditText.text.toString()) {
                binding.status = true
                interaction?.setResult(model, true)
            } else {
                binding.status = false
                interaction?.setResult(model, false)
            }
        }
    }
}
