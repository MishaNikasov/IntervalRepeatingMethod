package com.nikasov.intervalrepeatingmethod.ui.adapter.recycler

import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.common.extentions.showPopUpMenu
import com.nikasov.intervalrepeatingmethod.ui.entity.WordModel
import com.nikasov.intervalrepeatingmethod.databinding.ItemWordBinding
import com.nikasov.intervalrepeatingmethod.ui.adapter.base.BaseAdapter
import javax.inject.Inject

class WordAdapter @Inject constructor() : BaseAdapter<WordModel, ItemWordBinding>() {

    var interaction: Interaction? = null

    override var differ = AsyncListDiffer(this, callback)
    override var layoutId = R.layout.item_word

    override fun bind(binding: ItemWordBinding, model: WordModel, position: Int) {
        binding.root.setOnClickListener { interaction?.onItemSelected(position, model) }

        val popupClickListener = PopupMenu.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_word -> {
                    model.id?.let { id ->
                        interaction?.deleteWord(id)
                    }
                    true
                }
                else -> true
            }
        }

        binding.wordCardRoot.setOnLongClickListener {
            it.showPopUpMenu(R.menu.word_popup_menu, popupClickListener)
            return@setOnLongClickListener true
        }

        binding.word = model
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: WordModel)
        fun deleteWord(id: Int)
    }
}
