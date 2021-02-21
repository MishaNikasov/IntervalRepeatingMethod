package com.nikasov.intervalrepeatingmethod.ui.adapter.pager

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.databinding.ItemCarouselBinding
import javax.inject.Inject

class CarouselAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var interaction: Interaction? = null

    val callback = object : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CarouselViewHolder(
            ItemCarouselBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CarouselViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Word>) {
        differ.submitList(list)
    }

    inner class CarouselViewHolder constructor(
        private val binding: ItemCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) = with(itemView) {
            binding.word = word
            binding.checkBtn.setOnClickListener {
                if (word.rus == binding.rusEditText.text.toString()) {
                    binding.status = true
                    interaction?.setResult(word, true)
                } else {
                    binding.status = false
                    interaction?.setResult(word, false)
                }
            }
        }
    }

    interface Interaction {
        fun setResult(word: Word, isCompleted: Boolean)
    }
}
