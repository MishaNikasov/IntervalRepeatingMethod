package com.nikasov.intervalrepeatingmethod.ui.adapter.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.intervalrepeatingmethod.R
import com.nikasov.intervalrepeatingmethod.common.extentions.showPopUpMenu
import com.nikasov.intervalrepeatingmethod.ui.entity.WordModel
import com.nikasov.intervalrepeatingmethod.databinding.ItemWordBinding
import javax.inject.Inject

class WordAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var interaction: Interaction? = null

    private val callback = object : DiffUtil.ItemCallback<WordModel>() {

        override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return WordViewHolder(
            ItemWordBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WordViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<WordModel>) {
        differ.submitList(list)
    }

    inner class WordViewHolder constructor(
        private val binding: ItemWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(wordModel: WordModel) = with(itemView) {
            itemView.setOnClickListener { interaction?.onItemSelected(bindingAdapterPosition, wordModel) }

            val popupClickListener = PopupMenu.OnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete_word -> {
                        wordModel.id?.let { id ->
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
            binding.word = wordModel
        }

    }


    interface Interaction {
        fun onItemSelected(position: Int, item: WordModel)
        fun deleteWord(id: Int)
    }
}
